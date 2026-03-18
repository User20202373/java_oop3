package io.ch17;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(" === To Do 리스트 ===");
        System.out.println("1. 할 일 추가");
        System.out.println("2. 전체 목록 보기");
        System.out.println("3. 완료 처리");
        System.out.println("4. 미완료 목록만 보기");
        System.out.println("5. 완료 취소"); //[v] 자바 ---> [ ] 자바
        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            System.out.print("추가할 할 일을 입력하세요 :");
            String task = sc.nextLine();
            addTask(task);
        } else if (choice.equals("2")) {
            printTask();
        } else if (choice.equals("3")) {
           printTask();
            System.out.print("완료할 번호 입력 : ");
            int taskNum = sc.nextInt();
            sc.nextLine();
            completeTask(taskNum);
        } else if (choice.equals("4")) {
            pendingTask();
        } else if (choice.equals("5")) {
            printTask();
            System.out.print("완료 취소 할 번호 : ");
            int taskNum = sc.nextInt();
            sc.nextLine();
            cancelTask(taskNum);
        }

        sc.close();
    }

    //1. 할 일 추가
    private static void addTask(String task) {
        // "[ ] 할 일 내용" 형식으로 지정
        // [ ] -> 미완료 상태
        // [V] -> 완료 상태

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", true))){
            bw.write("[ ] " + task);
            bw.newLine();
            bw.flush();
            System.out.println("추가되었습니다 : " + task);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //2.전체 목록 보기
    private static void printTask() {
        System.out.println("\n===전체 할 일 목록===");
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            String line;
            while ((line = br.readLine()) != null ) {
                System.out.println(++count + ". " + line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //3. 완료 처리
    private static void completeTask(int taskNum) {
        ArrayList<String> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                if (count == taskNum) {
                    line = line.replace("[ ]", "[V]");
                    taskList.add(line);
                } else {
                    taskList.add(line);
                }
                count++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {
            for (String task : taskList) {
                bw.write(task);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("완료처리 되었습니다 : " + taskList.get(taskNum - 1));
    }


    //4.미완료 목록만 보기
    private static void pendingTask() {
        System.out.println("=== 미완료 목록 ===");

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            String checkBox;
            int count = 0;
            while ((checkBox = br.readLine()) != null) {
                if (checkBox.contains("[ ]")) {
                    System.out.println(checkBox);
                    count++;
                }
            }
            System.out.println("\n남은 할 일 : " + count + "개");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //5.완료 취소
    private static void cancelTask(int taskNum) {
        ArrayList<String> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            int count = 1;
            String line;

            while ((line = br.readLine()) != null) {
                if (count == taskNum) {
                    line = line.replace("[V]", "[ ]");
                    taskList.add(line);
                } else {
                    taskList.add(line);
                }
                count++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {
            for (String task : taskList) {
                bw.write(task);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("완료 취소 처리되었습니다 : " + taskList.get(taskNum - 1));
    }


}//end of class
