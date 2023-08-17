package com.memorisehelper.clientTouch;

import java.io.IOException;
import java.util.Scanner;

import com.memorisehelper.messages.Messages;
import com.memorisehelper.user.User;
import com.memorisehelper.utils.MemoriseUtils;

public class StartApp {

    private Scanner scan = new Scanner(System.in);
    private final static StartApp INSTANCE = new StartApp();
    private User user;
    private LibraryWorker worker = LibraryWorker.getWorker();

    private StartApp() {}


    public static StartApp getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IOException {
        INSTANCE.appStarts();
    }

    private void appStarts() throws IOException {

        System.out.println(Messages.greetings);
        String userName = scan.nextLine();
        user = User.getUser();
        user.setName(userName);
        System.out.println("Hello diar " + userName + " good time to learn some new word!");
        mainMenuUserChose();
    }

    public void mainMenuUserChose() throws IOException {

        System.out.println("What do you prefer to choose?");
        System.out.println("1. Create new library.");
        System.out.println("2. Show all my libraries.");
        System.out.println("3. Change content of library.");
        System.out.println("4. Starting to learn words.");
        System.out.println("5. Exit");
        System.out.println("Please write down your choose");
        crossroad(MemoriseUtils.writeInt());
    }

    private void crossroad(int userChoose) throws IOException {
        switch(userChoose) {
            case 1 -> worker.createLibrary();
            case 2 -> worker.showAllLibraries();
			case 3 -> worker.changeLibrary();
			case 4 -> worker.startLearning();
            case 5 -> {
                System.out.println("Goodbye diar " + user.getUserName() + "good luck!");
            }
            default -> {
                System.out.println("Maybe you wrote incorrect number, please try agane");
                System.out.println("Please write down your choose");
                crossroad(MemoriseUtils.writeInt());
            }
        }

    }
}
