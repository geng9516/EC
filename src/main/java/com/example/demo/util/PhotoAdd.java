package com.example.demo.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public class PhotoAdd {

	private PhotoAdd() {}

	public static File AddPhoto(MultipartFile photo,String path) {
		File newName = null;
		if (!photo.isEmpty()) {
			try {
				RandomOrderNumber ron = new RandomOrderNumber();
				String name = ron.random();
				File oldName = new File(photo.getOriginalFilename());
				newName = new File(name + ".jpg");
				oldName.renameTo(newName);
				byte[] bytes = photo.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path + newName)));
				stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newName;
	}
}
