import java.util.Scanner;

public class Field {
    private char empty;
    private char robot;
    private char trace;
    private char data[][];
    private Scanner scanner;
    private boolean trace_on;

    private class Robot {
        public int x;
        public int y;

        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Robot rob;

    public Field(int rows, int columns, char empty, char robot, char trace, int x, int y) {
        this.data = new char[rows][columns];
        this.empty = empty;
        this.robot = robot;
        this.trace = trace;
        this.rob = new Robot(x, y);
        this.scanner = new Scanner(System.in);


        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (x == j && y == i) {
                    data[i][j] = robot;
                } else {
                    data[i][j] = empty;
                }
            }
        }
    }

    public void printField() {
        for (int i = 0; i < this.data.length; i++) {
            String string_row = "";
            for (int j = 0; j < this.data[i].length; j++) {
                string_row += data[i][j];
            }
            System.out.println(string_row);
        }
    }
    public void setTrace_on(boolean trace_on)
    {
        this.trace_on = trace_on;
    }

    public void moveRight(int distance) {
        if (distance < 0) {
            System.out.println("Wrong distance");
            return;
        }
        int old_x = this.rob.x;
        if (this.rob.x + distance < data[0].length) {
            this.rob.x += distance;
        } else this.rob.x = data[0].length - 1;
        this.data[this.rob.y][old_x] = this.empty;
        this.data[this.rob.y][this.rob.x] = this.robot;
        if (this.trace_on) {
            for (int i = old_x; i < this.rob.x; i++) {
                data[this.rob.y][i] = this.trace;
            }
        }
        else
        {
            for (int i = old_x; i < this.rob.x; i++) {
                data[this.rob.y][i] = this.empty;
            }
        }
    }

    public void moveLeft(int distance) {
        if (distance < 0) {
            System.out.println("Wrong distance");
            return;
        }
        int old_x = this.rob.x;
        if (this.rob.x - distance >= 0) {
            this.rob.x -= distance;
        } else this.rob.x = 0;
        this.data[this.rob.y][old_x] = this.empty;
        this.data[this.rob.y][this.rob.x] = this.robot;
        if (this.trace_on) {
            for (int i = old_x; i > this.rob.x; i--) {
                data[this.rob.y][i] = this.trace;
            }
        }
        else
        {
            for (int i = old_x; i > this.rob.x; i--) {
                data[this.rob.y][i] = this.empty;
            }
        }
    }

    public void moveUp(int distance) {
        if (distance < 0) {
            System.out.println("Wrong distance");
            return;
        }
        int old_y = this.rob.y;
        if (this.rob.y - distance >= 0) {
            this.rob.y -= distance;
        } else this.rob.y = 0;
        this.data[this.rob.y][this.rob.x] = this.robot;
        this.data[old_y][this.rob.x] = this.empty;
        if (this.trace_on) {
            for (int i = old_y; i > this.rob.y; i--) {
                data[i][this.rob.x] = this.trace;
            }
        }
        else
        {
            for (int i = old_y; i > this.rob.y; i--) {
                data[i][this.rob.x] = this.empty;
            }
        }
    }

    public void moveDown(int distance) {
        if (distance < 0) {
            System.out.println("Wrong distance");
            return;
        }
        int old_y = this.rob.y;
        if (this.rob.y + distance < data.length) {
            this.rob.y += distance;
        } else this.rob.y = data.length - 1;
        this.data[this.rob.y][this.rob.x] = this.robot;
        this.data[old_y][this.rob.x] = this.empty;
        if (this.trace_on) {
            for (int i = old_y; i < this.rob.y; i++) {
                data[i][this.rob.x] = this.trace;
            }
        }
        else
        {
            for (int i = old_y; i < this.rob.y; i++) {
                data[i][this.rob.x] = this.empty;
            }
        }
    }

    public boolean execute()
    {
        String cmd = this.scanner.next();
        try {
            switch (cmd.trim().toLowerCase()) {
                case "exit":
                case "quit":
                    return false;
                case "left":
                    this.moveLeft(Integer.parseInt(this.scanner.next()));
                    this.printField();
                    return true;
                case "right":
                    this.moveRight(Integer.parseInt(this.scanner.next()));
                    this.printField();
                    return true;
                case "up":
                    this.moveUp(Integer.parseInt(this.scanner.next()));
                    this.printField();
                    return true;
                case "down":
                    this.moveDown(Integer.parseInt(this.scanner.next()));
                    this.printField();
                    return true;
                case "trace":
                    this.setTrace_on(this.scanner.next().equals("on") ? true : false);
                    return true;
                case "print":
                    this.printField();
                    return true;
                default:
                    System.out.println("Такой команды не существует: " + cmd);
                    return true;
            }
        } catch (Exception e) {
            System.out.println("Параметром команд left right up down может быть только целое число");
            return true;
        }
    }
}


class RunField {
    public static void main(String[] args) {
        Field f = new Field(10, 10, '-', '#', '*', 0, 0);
        while(f.execute()){}
    }
}
