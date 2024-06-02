package test;

import main.pet.Person5;
import main.pet.ResizableDesktopPet;

public class ResizableDesktopPetTest {
    // TODO
    public static void main(String[] args) {
        ResizableDesktopPet.getInstance().build(new Person5());
        ResizableDesktopPet.getInstance().setInitialPosition(1000, 720);
        ResizableDesktopPet.getInstance().setMoveSpeed(1, 0); //dx !> 5
    }
}
