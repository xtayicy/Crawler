package harry.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 生活文件工具
 * 
 * @author harry
 *
 */
public final class FileUtil {
	private static final String BLANK = " ";
	private static final String EQUALS = "=";

	private FileUtil() {}
	
	public static final void append(File file,String content) throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)))) {
			writer.write(content);
			writer.write("\r\n");
		}
	}
}
