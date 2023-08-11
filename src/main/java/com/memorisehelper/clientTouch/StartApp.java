package com.memorisehelper.clientTouch;

import java.util.Scanner;

import com.memorisehelper.libraries.LibraryWorker;
import com.memorisehelper.messages.Messages;
import com.memorisehelper.utils.MemoriseUtils;

public class StartApp {

    private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        StartApp sa = new StartApp();
        sa.appStarts();
    }

    private void appStarts() {

        System.out.println(Messages.greetings);
        String userName = scan.nextLine();
        System.out.println("Hello diar " + userName + " good time to learn some new word!");
        crossroad(mainMenuUserChose());
    }

    private int mainMenuUserChose() {

        System.out.println("What do you prefer to choose?");
        System.out.println("1. Create new library.");
        System.out.println("2. Show all my libraries.");
        System.out.println("3. Change content of library.");
        System.out.println("4. Starting to learn words.");
        System.out.println("Please write down your choose");
        return MemoriseUtils.writeInt();
    }

    private void crossroad(int userChoose) {
        switch(userChoose) {
            case 1 -> new LibraryWorker().createLibrary();
            case 2 -> new LibraryWorker().showAllLibraries();
			case 3 -> new LibraryWorker().changeLibrary();
			case 4 -> new LibraryWorker().startLearning();
            default -> {
                System.out.println("Maybe you wrote incorrect number, please try agane");
                System.out.println("Please write down your choose");
                crossroad(MemoriseUtils.writeInt());
            }
        }

    }
}
