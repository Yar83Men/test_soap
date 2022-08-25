package org.test;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;
public class UncompressZip {
    public static void decompressGzipFile(Path gzipFile, Path newFile) {
        try (FileInputStream fis = new FileInputStream(gzipFile.toFile());
             GZIPInputStream gis = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(newFile.toFile());
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decompress(final byte[] compressed) {
        if (isNull(compressed) || compressed.length == 0) {
            return null;
        }
        try (final GZIPInputStream gzipInput = new GZIPInputStream(new ByteArrayInputStream(compressed));
             final StringWriter stringWriter = new StringWriter()) {
            IOUtils.copy(gzipInput, stringWriter, UTF_8);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UncheckedIOException("Ошибка при распаковке сжатых данных!", e);
        }
    }

//    public static boolean isCompressed(final byte[] compressed) {
//        return compressed.length > 1 && (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
//                && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
//    }
}