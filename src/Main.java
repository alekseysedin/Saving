import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String way, GameProgress game) {

    }

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 12, 15, 1.0);
        GameProgress game2 = new GameProgress(29, 20, 26, 2.1);
        GameProgress game3 = new GameProgress(39, 34, 35, 3.3);

        try (FileOutputStream fos1 = new FileOutputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save1.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos1)) {
            oos.writeObject(game1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos2 = new FileOutputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save2.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos2)) {
            oos.writeObject(game2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos3 = new FileOutputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save3.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos3)) {
            oos.writeObject(game3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/zip_output.zip"));
             FileInputStream fis = new FileInputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save1.dat");
             FileInputStream fis2 = new FileInputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save2.dat");
             FileInputStream fis3 = new FileInputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save3.dat")) {
            ZipEntry entry = new ZipEntry("packed_save1.dat");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            byte[] buffer2 = new byte[fis2.available()];
            byte[] buffer3 = new byte[fis3.available()];
            fis.read(buffer);
            zout.write(buffer);
            ZipEntry entry2 = new ZipEntry("packed_save2.dat");
            zout.putNextEntry(entry2);
            fis2.read(buffer2);
            zout.write(buffer2);
            ZipEntry entry3 = new ZipEntry("packed_save3.dat");
            zout.putNextEntry(entry3);
            fis3.read(buffer3);
            zout.write(buffer3);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        File newDir = new File("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save1.dat");
        newDir.delete();
        File newDir2 = new File("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save2.dat");
        newDir2.delete();
        File newDir3 = new File("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save3.dat");
        newDir3.delete();


    }
}
