package eu.bigan.fed.edelivery.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Texto sobre lo que hace esta clase.
 * @author jorgebernalromero
 *
 */
public class FileManager{
	
    /**
     * 
     */
    public static byte[] readFileAsBytes(File file) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")){
            byte[] bytes = new byte[(int) accessFile.length()];
            accessFile.readFully(bytes);
            return bytes;
        }
    }

    /**
     * 
     */
    public void writeFileAsBytes(byte[] bytes, String fileName) throws IOException {
        File file = new File(fileName);

        try (RandomAccessFile accessFile = new RandomAccessFile(file, "w")){
            accessFile.write(bytes);
        }
    }

    /**
     * 
     */
    public static void writeBytesToFile(File receivedFile, byte[] fileBytes) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(receivedFile)){
            fos.write(fileBytes);
        }
    }
}
