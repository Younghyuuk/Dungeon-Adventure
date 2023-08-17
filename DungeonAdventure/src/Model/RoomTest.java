package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    public final void createRoomAll4Doors() {
        int allDoors = 0;
        Room room = new Room(allDoors);
        assertTrue(hasDoor(room));

        String expected =
                "1 1 1 3 1 1 1 \n" +
                "1 0 0 0 0 0 1 \n" +
                "1 0 0 0 0 0 1 \n" +
                "3 0 0 0 0 0 3 \n" +
                "1 0 0 0 0 0 1 \n" +
                "1 0 0 0 0 0 1 \n" +
                "1 1 1 3 1 1 1 \n";
        assertEquals(expected, room.toString());
    }

    @Test
    public final void addDoorMiddleRoom() {
        int eastDoor = 2;
        int west = 0;
        Room room = new Room(eastDoor);
        room.addDoor(west);

        String expected =   "1 1 1 1 1 1 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "3 0 0 0 0 0 3 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 1 1 1 1 1 1 \n";
        assertEquals(expected, room.toString());
    }

    @Test
    public final void addDoorWhereAnotherDoorIs() {
        int eastDoor = 2;
        int east = 2;
        Room room = new Room(eastDoor);
        room.addDoor(east);

        String expected =   "1 1 1 1 1 1 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 3 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 1 1 1 1 1 1 \n";
        assertEquals(expected, room.toString());
    }

    @Test
    public final void addInvalidDoor() {
        int eastDoor = 2;
        int invalidDoor = -1;
        Room room = new Room(eastDoor);
        room.addDoor(invalidDoor);

        String expected =   "1 1 1 1 1 1 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 3 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 1 1 1 1 1 1 \n";
        assertEquals(expected, room.toString());
    }

    @Test
    public final void createRoomWithInvalidDoor() {
        int invalidDoor = -1;
        Room room = new Room(invalidDoor);
        assertFalse(hasDoor(room));

        String expected =   "1 1 1 1 1 1 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 0 0 0 0 0 1 \n" +
                            "1 1 1 1 1 1 1 \n";
        assertEquals(expected, room.toString());
    }

    // ---------------------------------------Helper methods--------------------------------------- \\
    private boolean hasDoor(final Room theRoom) {
        for (int i = 0; i < theRoom.getRoomHeight(); i++) {
            for (int j = 0; j < theRoom.getRoomWidth(); j++) {
                if(theRoom.getRoom()[i][j].equals("3")) {
                    return true;
                }
            }
        }

        return false;
    }

}