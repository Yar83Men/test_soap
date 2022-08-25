package org.test;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileWalkTree {
    public static void main(String[] args) throws IOException {
        String pathToFiles = "src/main/resources";
        Path path = Paths.get(pathToFiles);
        Files.walkFileTree(path, new MyFileVisitor());
    }
}

class MyFileVisitor implements FileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //System.out.println("Enter to directory: " +  dir );
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String pathToUncompressed = "src/main/resources/1";
        UncompressZip.decompressGzipFile(file, Paths.get(pathToUncompressed));
        //System.out.println("File name :" + file.getFileName());
//        byte [] arr = Files.readAllBytes(file);
//        UncompressZip.decompress(arr);

        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.TERMINATE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }


}
