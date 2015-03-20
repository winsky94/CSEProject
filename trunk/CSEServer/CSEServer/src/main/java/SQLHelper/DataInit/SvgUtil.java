package SQLHelper.DataInit;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import SQLHelper.FileList;

/**
 * svg转换工具类(以下方法开发足够用了)
 * 
 * @param svg
 * @param pdf
 * @throws IOException
 * @throws TranscoderException
 */
public class SvgUtil {

	public static void main(String[] args) throws Exception {
		// 注：使用的是svg字符串转pdf的情况可能会出现编码错误的异常，就把字符串里的UTF-8替换为GBK
	/*	File f = new File("src/ATL.svg");
		File destFile = new File("src/sun.png");
		SvgUtil.convertSvgFileToPng(f, destFile);
	*/
		FileList fList=new FileList("src/迭代一数据/teams");
		ArrayList<String> paths=fList.getList();
		paths.remove("E:\\CSEProject\\trunk\\CSEServer\\CSEServer\\src\\迭代一数据\\teams\\teams");
		for(String path:paths){
			File f=new File(path);
			String[] strings=path.split("teams");
			String ii=strings[1].replace(".svg", ".png");
			String newPath="src\\迭代一数据\\teamsPng"+ii;
			File destFile=new File(newPath);
			SvgUtil.convertSvgFileToPng(f, destFile);
			System.out.println("成功转成转换"+path);
		}
		
	}

	// svg文件转成pdf
	public static void convertSvgFileToPdf(File svg, File pdf)
			throws IOException, TranscoderException {
		InputStream in = new FileInputStream(svg);
		OutputStream out = new FileOutputStream(pdf);
		out = new BufferedOutputStream(out);
		convertToPdf(in, out);
	}

	private static void convertToPdf(InputStream in, OutputStream out)
			throws IOException, TranscoderException {
		Transcoder transcoder = new PNGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	// svg转为png
	public static void convertSvgFileToPng(File svg, File png)
			throws IOException, TranscoderException {
		InputStream in = new FileInputStream(svg);
		OutputStream out = new FileOutputStream(png);
		out = new BufferedOutputStream(out);
		convertToPNG(in, out);
	}

	private static void convertToPNG(InputStream in, OutputStream out)
			throws IOException, TranscoderException {
		Transcoder transcoder = new PNGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	// 字符串转成pdf
	public static void convertStrToPdf(String string, File pdf) throws IOException,
			TranscoderException {
		InputStream in = new ByteArrayInputStream(string.getBytes());
		OutputStream out = new FileOutputStream(pdf);
		out = new BufferedOutputStream(out);
		convertToPdf(in, out);
	}

}