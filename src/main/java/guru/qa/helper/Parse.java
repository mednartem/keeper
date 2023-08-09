package guru.qa.helper;

import javax.swing.*;

public class Parse {

    public static int parseString(String textShowInputDialog) {
        int number;

        try {
            number = Integer.parseInt(
                    JOptionPane.showInputDialog(textShowInputDialog)
            );
        } catch (NumberFormatException e) {
            number = 0;
        }

        return number;
    }
}
