public class Intervals {
    public static String[] intevals = {"","m2","M2","m3","M3","P4","P5","m6","M6","m7","M7","P8"};
    public static String[] notes = {"C","D","E","F","G","A","B"};
    public static String[] semitons = {"C","-","-","D","-","-","E","-","F","-","-","G","-","-","A","-","-","B","-"};

    static int semiton;
    static int degrees;

    public static String intervalConstruction(String[] args) {
        String inInteval = args[0];
        String inNote = args[1];
        String upOrLow = null;
        String endNote = null;

        int startNoteId = 0;
        int startSemitonId = 0;
        int endNoteId = 0;
        int endSemitonId = 0;
        int semitonsCount = 0;
        int semitonDif = 0;
        int it = 0;
        int intervalAc = 0;
        int noteAc = 0;

        //Проверка на превышения массива.
        if(args.length > 3 || args.length <= 1){
            throw new RuntimeException("Illegal number of elements in input array");
        }

        //Проверкана наличие 3 элемента входного массива.Если отсутствует,то указатель принимает значение "abs".
        if(args.length == 2){
            upOrLow = "asc";
        }else{
            upOrLow = args[2];
        }

        if(upOrLow == "asc" || upOrLow == "dsc"){
        }else{
            throw new RuntimeException("Intervals can be ascending or descending");
        }

        //Проверка на наличие интевала в массиве
        for (int i = 0; i < intevals.length; i++){
            if(inInteval.equals(intevals[i])){
                intervalAc = 1;
                break;
            }
        }
        if(intervalAc == 0){
            throw new RuntimeException("There's no such interval");
        }

        // Находит интервал из масива и получает его занчение.
        for(int i = 0; i < intevals.length; i++){

            if(inInteval == intevals[i]){
                degrees = Integer.parseInt(String.valueOf(intevals[i].charAt(1)));
                if(i > 5){semiton = i+1;
                }else{semiton = i;}

            }
        }

        //Проверка на наличи ноты в массиве
        for (int i = 0; i < notes.length; i++){
            if(String.valueOf(inNote.charAt(0)).equals(notes[i])){
                noteAc = 1;
            }
        }
        if(noteAc == 0){
            throw new RuntimeException("There's no such note");
        }


        //Находит индекс первой ноты в массиве нот.
        for(int i = 0; i < notes.length; i++){
            if(String.valueOf(inNote.charAt(0)).equals(notes[i])){
                startNoteId = i;
                break;
            }
        }

        //Находит индекс последней ноты в массиве нот.
        it = startNoteId;

        if(upOrLow.equals("asc")) {
            for (int i = 0; i != degrees; i++) {
                if (it > notes.length - 1) {
                    it = 0;
                }
                endNoteId = it;
                it++;
            }
        }else if(upOrLow.equals("dsc")){
            for (int i = 0; i != degrees; i++) {
                if (it < 0) {
                    it = notes.length-1;
                }
                endNoteId = it;
                it--;
            }
        }

        endNote = notes[endNoteId];

        //Нахождение индекса первого и последнего элемента в массиве полунот и получение их значение.
        for(int i = 0; i < semitons.length; i++){
            if(inNote.contains(semitons[i])){
                startSemitonId = i;
            }
        }
        for (int i = 0; i < semitons.length; i++){
            if(endNote.contains(semitons[i])){
                endSemitonId = i;
            }
        }

        //Подсчёт количества полунот от первго до последнего полутона в массиве полутонов с учётом указателя.
        it = startSemitonId;
        if(upOrLow.equals("asc")) {
            while (it != endSemitonId) {
                if (it > semitons.length - 1) {
                    it = 0;
                }
                if (semitons[it].equals("-")) {
                    semitonsCount++;
                }
                it++;
            }
        }else if(upOrLow.equals("dsc")){
            while (it != endSemitonId) {
                if (it == 0) {
                    it = semitons.length-1;
                }
                if (semitons[it].equals("-")) {
                    semitonsCount++;
                }
                it--;
            }
        }

        //Изменение счётчика полунот если на вход пришла полунота.
        if(upOrLow.equals("asc")){
            if(inNote.contains("b")){
                semitonsCount++;
            }else if(inNote.contains("#")){
                semitonsCount--;
            }

        }else if(upOrLow.equals("dsc")){
            if(inNote.contains("b")){
                semitonsCount--;
            }else if(inNote.contains("#")){
                semitonsCount++;
            }
        }

        //Вычисление разности полутонов(Интервальные полутона - счётчик полутонов)
        semitonDif = semiton-semitonsCount;

        //На основе  разницы изменяеться последняя нота.Поднимаем или опускаем на  полутон.
        if(upOrLow.equals("asc")){
            switch (semitonDif){
                case 1:
                    endNote += "#";
                    break;
                case 2:
                    endNote += "##";
                    break;
                case -1:
                    endNote += "b";
                    break;
                case -2:
                    endNote += "bb";
                    break;
                case 0:
                    break;
            }

        }else if(upOrLow.equals("dsc")){
            switch (semitonDif){
                case 1:
                    endNote += "b";
                    break;
                case 2:
                    endNote += "bb";
                    break;
                case -1:
                    endNote += "#";
                    break;
                case -2:
                    endNote += "##";
                    break;
                case 0:
                    break;
            }
        }

        if(inInteval.equals(intevals[intevals.length-1])){
            endNote = inNote;
        }

        return endNote;
    }

    public static String intervalIdentification(String[] args) {
        String endInterval = null;
        String firstNote = null;
        String lastNote = null;
        String upOrLow = null;

        int firstNoteId = 0;
        int lastNoteId = 0;
        int deagrease = 1;
        int fistNoteSemitonId = 0;
        int lastNoteSemitonId = 0;
        int it = 0;
        int semitonsCount = 0;
        int firstNoteAc = 0;
        int lastNoteAc = 0;



        //Проверка на размер принемаемого массива.
        if(args.length > 3 || args.length <= 1){
            throw new RuntimeException("Illegal number of elements in input array");
        }


        firstNote = args[0];
        lastNote = args[1];

        //Проверка на наличие нот в масиве.
        for (int i = 0; i < notes.length; i++){
            if(String.valueOf(firstNote.charAt(0)).equals(notes[i])){
                firstNoteAc = 1;
            }
            if(String.valueOf(lastNote.charAt(0)).equals(notes[i])){
                lastNoteAc = 1;
            }
        }
        if(firstNoteAc == 0 || lastNoteAc == 0){
            throw new RuntimeException("There's no such note");
        }

        //Изменение указателя в зависимости от наличия значения
        if(args.length == 2){
            upOrLow = "asc";
        }else{
            upOrLow = args[2];
        }

        //Выбрасывание ошибки если есть несоответствие указателя
        if(upOrLow.equals("asc") || upOrLow.equals("dsc")){}else{
            throw new RuntimeException("Intervals can be ascending or descending");
        }

        //Нахождение нот в массиве с нотами для дальнейшего расчёта градусов.
        for(int i = 0; i < notes.length; i++){
            if(String.valueOf(firstNote.charAt(0)).equals(notes[i])){
                firstNoteId = i;
                break;
            }
        }
        for(int i = 0; i < notes.length; i++){
            if(String.valueOf(lastNote.charAt(0)).equals(notes[i])){
                lastNoteId = i;
                break;
            }
        }

        //Подсчёт градусов между нотами
        if(upOrLow.equals("asc")){
            for(int i = firstNoteId; i != lastNoteId;){
                if(i > notes.length-1){i = 0;}
                if(i == lastNoteId){break;}
                deagrease++;
                i++;
            }
        } else if(upOrLow.equals("dsc")) {
            for(int i = firstNoteId; i != lastNoteId;){
                if(i < 0){i = notes.length-1;}
                if(i == lastNoteId){break;}
                deagrease++;
                i--;
            }
        }

        //Определение индексов первой и второй ноты в масиве полутонов
        for(int i = 0; i < semitons.length; i++){
            if(String.valueOf(firstNote.charAt(0)).equals(semitons[i])){
                fistNoteSemitonId = i;
            }
        }
        for (int i = 0; i < semitons.length; i++){
            if(String.valueOf(lastNote.charAt(0)).equals(semitons[i])){
                lastNoteSemitonId = i;
            }
        }


        //Подсчёт количества полутонов между элементами
        it = fistNoteSemitonId;
        if(upOrLow.equals("asc")) {
            while (it != lastNoteSemitonId) {
                if (it > semitons.length - 1) {
                    it = 0;
                }
                if (semitons[it].equals("-")) {
                    semitonsCount++;
                }
                it++;
            }
        }else if(upOrLow.equals("dsc")){
            while (it != lastNoteSemitonId) {
                if (it == 0) {
                    it = semitons.length-1;
                }
                if (semitons[it].equals("-")) {
                    semitonsCount++;
                }
                it--;
            }
        }

        //Изменение итогово количества полутонов в зависимости от полученой ноты.
        if(upOrLow.equals("asc")){
            if(firstNote.length() == 2){
            if(firstNote.contains("b")){
                semitonsCount++;
            }else if(firstNote.contains("#")){
                semitonsCount--;
            }}else if(firstNote.length() > 2){
                String s = String.valueOf(firstNote.charAt(1))+String.valueOf(firstNote.charAt(2));
                if(s.equals("bb")){
                    semitonsCount+=2;
                }else if(s.equals("##")){
                    semitonsCount--;
                }
            }

        }else if(upOrLow.equals("dsc")){
            if (firstNote.length() == 2){
            if(firstNote.contains("b")){
                semitonsCount--;
            }else if(firstNote.contains("#")){
                semitonsCount++;
            }}
            else if(firstNote.length() >2){
                String s = String.valueOf(firstNote.charAt(1))+String.valueOf(firstNote.charAt(2));
                if(s.equals("bb")){
                    semitonsCount++;
                }else if(s.equals("##")){
                    semitonsCount--;
                }
            }
        }
        if(upOrLow.equals("asc")){
            if(lastNote.length() == 2){
                if(firstNote.contains("b")){
                    semitonsCount--;
                }else if(lastNote.contains("#")){
                    semitonsCount++;
                }}else if(lastNote.length() > 2){
                String s = String.valueOf(lastNote.charAt(1))+String.valueOf(lastNote.charAt(2));
                if(s.equals("bb")){
                    semitonsCount-=2;
                }else if(s.equals("##")){
                    semitonsCount++;
                }
            }

        }else if(upOrLow.equals("dsc")){
            if (lastNote.length() == 2){
                if(lastNote.contains("b")){
                    semitonsCount++;
                }else if(lastNote.contains("#")){
                    semitonsCount--;
                }}
            else if(lastNote.length() >2){
                String s = String.valueOf(lastNote.charAt(1))+String.valueOf(lastNote.charAt(2));
                if(s.equals("bb")){
                    semitonsCount+=2;
                }else if(s.equals("##")){
                    semitonsCount--;
                }
            }
        }

        //Полутона не могут быть отрицательными.
        if(semitonsCount < 0){semitonsCount*=(-1);}

        //Нахождение интервала из масива полутонов
        for(int i = 1; i < intevals.length; i++){
            int semiton = i;
            if(i > 5){
                semiton++;
            }
            if(deagrease == Integer.parseInt(String.valueOf(intevals[i].charAt(1))) & semitonsCount == semiton){
                endInterval = intevals[i];
            }
        }


        //Выдаём ошибку если интервал не найден
        if(semitonsCount == 0 & deagrease == 1){
            endInterval = intevals[intevals.length-1];
        }else if(endInterval != null){
            return endInterval;
        }else{
            throw new  RuntimeException("Cannot identify the interval");
        }

        return endInterval;
    }

}
