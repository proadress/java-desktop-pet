package test;

import main.pet.*;

import javax.swing.*;

public class ResizableDesktopPetTest {
    // TODO
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ResizableDesktopPet pet = new ResizableDesktopPet(new Person5());
                pet.setInitialPosition(1000, 720);
                pet.setMoveSpeed(1, 0); //dx !> 5
            }
        });
    }
}
