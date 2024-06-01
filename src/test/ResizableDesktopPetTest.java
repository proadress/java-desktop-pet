package test;

import main.pet.*;
import main.pet.ResizableDesktopPet;

public class ResizableDesktopPetTest {
    // TODO
    public static void main(String[] args) {
        ResizableDesktopPet pet1 = new ResizableDesktopPet(new Person1());
        ResizableDesktopPet pet2 = new ResizableDesktopPet(new Person2());
        ResizableDesktopPet pet3 = new ResizableDesktopPet(new Person3());
        pet1.setInitialPosition(1000, 720);
        pet1.setMoveSpeed(1, 0); //dx !> 5
        pet2.setInitialPosition(700, 500);
        pet2.setMoveSpeed(1, -2); //dx !> 5
        pet3.setInitialPosition(1020, 760);
        pet3.setMoveSpeed(-1, -1); //dx !> 5
    }
}
