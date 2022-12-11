import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(GameProgress gameProgress, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delete(String fileName) {
        File newDir = new File(fileName);
        newDir.delete();
    }

    public static void doZip(ArrayList<String> file, ArrayList<String> fileZip) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/zip_output.zip"));
            for (int i = 0; i < file.size(); i++) {
                try (FileInputStream fis = new FileInputStream(file.get(i))) {
                    ZipEntry entry = new ZipEntry(fileZip.get(i));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            zout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(100, 12, 15, 1.0);
        GameProgress game2 = new GameProgress(29, 20, 26, 2.1);
        GameProgress game3 = new GameProgress(39, 34, 35, 3.3);

        ArrayList<String> file = new ArrayList<String>();
        file.add("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save1.dat");
        file.add("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save2.dat");
        file.add("C:/Users/aleks/IdeaProjects/Setup/Games/savegames/save3.dat");

        ArrayList<String> fileZip = new ArrayList<String>();
        fileZip.add("packed_save1.dat");
        fileZip.add("packed_save2.dat");
        fileZip.add("packed_save3.dat");

        for (String files : file) {
            saveGame(game1, files);
        }
        doZip(file, fileZip);

        for (String files : file) {
            delete(files);
        }

    }
}
