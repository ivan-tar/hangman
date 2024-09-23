//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.</br>
//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
// to see how IntelliJ IDEA suggests fixing it.</br>
//TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
// for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.</br>

//TIP <b>1.</b> нужно создать файл с каталогом слов </br>
// <b>2.</b> нужно достовать от туда слова и с ниму уже можно будет взаимодействовать </br>
// <b>3.</b> после того как достанется слово нужно посчитать колличество букв </br>
// <b>4.</b> Разбить слово на буквы (возможно создать массив где каждая ячейка это буква) </br>
// <b>5.</b> после нужно создать проверку массива,  </br>
// если i!= то *(и символы asci) если i== пишется буква из массива
// сделать проверку на размер букв
import java.io.*;
import java.util.*;


//создать функцию ошибок (проверять уникальные буквы и не записывать их в ошибки)
//сделать проверку на уникальность букв в слове
//создать функцию вывода картинок
//        создать цыкл искходя из (колличества уникальных букв в слове +6),
//    если нажать на n то i = 0, если i 0 то запустить заново все с генерации слова


public class Main {
    public static void main(String[] args)  {
        System.out.println("""
                 ▄▀▀▄ ▄▄   ▄▀▀█▄   ▄▀▀▄ ▀▄  ▄▀▀▀▀▄    ▄▀▀▄ ▄▀▄  ▄▀▀█▄   ▄▀▀▄ ▀▄
                █  █   ▄▀ ▐ ▄▀ ▀▄ █  █ █ █ █         █  █ ▀  █ ▐ ▄▀ ▀▄ █  █ █ █
                ▐  █▄▄▄█    █▄▄▄█ ▐  █  ▀█ █    ▀▄▄  ▐  █    █   █▄▄▄█ ▐  █  ▀█
                   █   █   ▄▀   █   █   █  █     █ █   █    █   ▄▀   █   █   █\s
                  ▄▀  ▄▀  █   ▄▀  ▄▀   █   ▐▀▄▄▄▄▀ ▐ ▄▀   ▄▀   █   ▄▀  ▄▀   █ \s
                 █   █    ▐   ▐   █    ▐   ▐         █    █    ▐   ▐   █    ▐ \s
                 ▐   ▐            ▐                  ▐    ▐            ▐      \s
                """);
//        getSetWords();
//        System.out.println(choiceOfWord(getSetWords()));
        String word = choiceOfWord(getSetWords());
        ArrayList<String> lettersWord = getWordLetters(word);
        ArrayList<String> hiddenLetters = getWordLetters(word);
        Collections.fill(hiddenLetters, "*");
        Set<String> setUniqueLetters = uniqueLetters(lettersWord);
        int crutch = 0;
        int errorCounter = 0;
        imageOutput(errorCounter);


        for(int i = 0; i < Integer.valueOf(setUniqueLetters.size()) + 6;i++){
            if ((lettersWord.equals(hiddenLetters))){
                System.out.println("\nВы победили ваше слово было : " + word + "\n");
                crutch = 1;
            }
            if (errorCounter > 5){
                System.out.println("\nВы проиграли ваше слово было : " + word + "\n");
                crutch = 1;
            }
            if (crutch == 1){
                word = choiceOfWord(getSetWords());
                lettersWord = getWordLetters(word);
                hiddenLetters = getWordLetters(word);
                Collections.fill(hiddenLetters, "*");
                setUniqueLetters = uniqueLetters(lettersWord);
                i= 0;
                errorCounter = 0;
                imageOutput(errorCounter);
            }

            wordOutput(hiddenLetters);
            Scanner in = new Scanner(System.in);
            System.out.print("\nВведите букву: ");
            String letter = in.nextLine().toLowerCase();
            if (checkErorrs(setUniqueLetters, letter)){
                hiddenLetters = letterSearch(lettersWord,hiddenLetters, letter);
                imageOutput(errorCounter);
                crutch = 0;
                i--;

            } else if (letter.equals("e")) {
                break;
            } else if (letter.equals("n")) {
                crutch = 1;
            } else {
                errorCounter += 1;
                crutch = 0;
                imageOutput(errorCounter);
                System.out.println("Колличество ошибок = " + errorCounter);

            }
        }
    }

    public static Set<String> getSetWords(){ //

        String separater = File.separator; // выбор системного слеша
        String path = "src" + separater + "words.txt"; // относительный путь (независим от стороней системы)

        File words = new File(path);

        Set<String> setWords = new HashSet<>();
        try (Scanner scanner = new Scanner(words)) {
            while (scanner.hasNextLine()) {
                setWords.add(String.valueOf(scanner.nextLine()).toLowerCase());
            }

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        return setWords;
    }
    public static String choiceOfWord (Set<String> setWords  ){
        int randomIndex = (int) (Math.random() * setWords.size());
        return (String) setWords.toArray()[randomIndex];
    }

    public static ArrayList<String> getWordLetters(String word){
        ArrayList<String> lettersWord = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            lettersWord.add(String.valueOf(word.charAt(i)));
        }
        return lettersWord;
    }

    public static ArrayList<String> letterSearch(ArrayList<String> lettersWord, ArrayList<String> hiddenLetters, String letter) {
        for (int i = 0; i < Integer.valueOf(lettersWord.size()); i++) {
            if (lettersWord.get(i).equals(letter)) {
                hiddenLetters.set(i, lettersWord.get(i));
            }
        }
        return hiddenLetters;
    }

    public static Set<String> uniqueLetters(ArrayList<String> lettersWord){
        Set<String> set = new HashSet<String>(lettersWord);
        return set;
    }

    public static boolean checkErorrs(Set<String> setUniqueLetters, String letter){
        boolean check = setUniqueLetters.contains(letter);
        return check;
    }

    public static void wordOutput (ArrayList<String> words){
        for(String let : words){
            System.out.print(let);
        }
    }

    public static void imageOutput (int errorCounter) {
        String art;
        if (errorCounter == 0){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀
                          █
                          █
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 1){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀         🞉
                          █
                          █
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 2){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀         🞉
                          █           []
                          █
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 3){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀         🞉
                          █          ⎧[]
                          █
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 4){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀         🞉
                          █          ⎧[]⎫
                          █
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 5){
            art = """
                          ▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █    ▄▀     ▐
                          █  ▄▀       ▐
                          █▄▀         🞉
                          █          ⎧[]⎫
                          █           |
                          █
                          █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                          █[E]xit  [N]ew game█
                          ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                      """;
            System.out.println(art);
        }
        if (errorCounter == 6) {
            art = """
                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
                           █    ▄▀     ▐
                           █  ▄▀       ▐
                           █▄▀         🞉
                           █          ⎧[]⎫
                           █           ||
                           █
                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
                           █[E]xit  [N]ew game█
                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
                       """;
            System.out.println(art);
        }
    }

//
//    public static String[] randomWord(String[] arrayWords) {
//        int random = (int) (Math.random() * arrayWords.length);
//        String randomWord = arrayWords[random];
//        String[] RWArr = randomWord.split("");
//        return RWArr;
//    }
//
//    public static String run(String[] arrW){
//
//        String[] randomWord = randomWord(arrW);
//
//        String[] riddle = new String[randomWord.length];
//        Arrays.fill(riddle, "*");
//
//
//        for (String word : randomWord) {
//            System.out.print(word);
//        }
//
//        System.out.println(" ");
//
//        Scanner in = new Scanner(System.in);
//
//        int errors = 0;
//
//        for (int x = 0; x < (randomWord.length +6) ; x++) {
//            String art;
//            if ( Arrays.equals(randomWord, riddle)) {
//                System.out.println("\n\n\nВы отгадали ваше слово было: " + String.join("",randomWord) + "\n\n\n");
//                dictonary();
//
//            }
//            System.out.println("\n\n\n");
//            if (errors == 0){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀
//                           █
//                           █
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 1){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █
//                           █
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 2){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █           []
//                           █
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 3){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █          ⎧[]
//                           █
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 4){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █          ⎧[]⎫
//                           █
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 5){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █          ⎧[]⎫
//                           █           |
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//            }
//            if (errors == 6){
//                art = """
//                           ▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █    ▄▀     ▐
//                           █  ▄▀       ▐
//                           █▄▀         🞉
//                           █          ⎧[]⎫
//                           █           ||
//                           █
//                           █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
//                           █[E]xit  [N]ew game█
//                           ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
//                       """;
//                System.out.println(art);
//                System.out.println("Вы проиграли ваше слово было: " + String.join("",randomWord) + "\n\n\n");
//                dictonary();
//            }
//
//            System.out.println("колличество ошибок = " + errors);
//
//            for (String w : riddle) {
//                System.out.print(w);
//            }
//
//            System.out.print("\nВведите букву: ");
//            String letter = in.nextLine().toLowerCase();
//
//            if (letter.equals("n") ){
//                dictonary();
//            } else if (letter.equals("e")) {
//                break;
//            }
//
//            String controlOld = String.join("", riddle);
//
//            check(randomWord,riddle,letter);
//
//            String controlNew = String.join("", riddle);
//
//            if (controlOld.equals(controlNew.toString())) {
//                errors++;
//            }
//
//        }
//        return "end";
//    }
}