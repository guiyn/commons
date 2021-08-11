package org.cmcc.ecip.examples.files;

import java.io.File;

import lombok.Data;

/**
 * 获取目录下所有文件 名称
 * 
 * @author guiyn
 *
 */
public class ListPath {

	static String filepath = "D:\\work.ecip\\xhzw\\target\\output\\lib";

	public static void main(String[] args) {

		File f = new File(filepath);

		for (String sf : f.list()) {
			String name = sf.substring(0, sf.length() - 4);
//			System.out.println(">>>>>"+name);
			version vv = new version();
			int index = name.lastIndexOf("-");
			if (index > 0) {
				String version = name.substring(index + 1);
				vv.setVersion(version);
				String g_id = name.substring(0, index);
				int i2 = g_id.indexOf("-");

				if (i2 > 0) {
					String g = g_id.substring(0, i2);
					String aid = g_id.substring(i2 + 1);
					vv.setGroup(g);
					vv.setAid(aid);
				}
				else
					vv.setAid(g_id);
			}

			else
				vv.setAid(name);

			System.out.println(vv);
		}

	}

}

@Data
class version {

	String group;
	String aid;
	String version;

	public String toString() {
		return (group==null?"":group)+"\t"+aid+"\t"+(version==null?"":version);
	}
}
