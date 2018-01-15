package com.wds.nc;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import ucar.ma2.*;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wangdongsong1229@163.com on 2018/1/13.
 */
public class NCFile {

    public static void main(String[] args) {
        NetcdfFile dataFile = null;
        String fileName = "E:\\GitHub\\java-exercises\\src\\main\\java\\com\\wds\\nc\\Z_NAFP_C_BABJ_20180112001013_P_CLDAS_RT_CHN_0P05_HOR-TEM-2018011200.GRB2";

        try {
            dataFile = NetcdfFile.open(fileName);
            System.out.println(dataFile.findVariable("Temperature_height_above_ground"));
            System.out.println(dataFile.findVariable("lat"));
            //Variable dataVar = dataFile.findVariable("data");
            //Variable refTime = dataFile.findVariable("refTime");
            Variable dataVar = dataFile.findVariable("Temperature_height_above_ground");
            Variable timeVar = dataFile.findVariable("time");
            System.out.println(timeVar.getUnitsString());
            Variable latVar = dataFile.findVariable("lat");
            Variable lonVar = dataFile.findVariable("lon");

            int latVarLength = latVar.getDimension(0).getLength();
            int lonVarLength = lonVar.getDimension(0).getLength();

            ArrayDouble timeArray = (ArrayDouble) timeVar.read();

            ArrayFloat latArray = (ArrayFloat) latVar.read();
            ArrayFloat lonArray = (ArrayFloat) lonVar.read();
            ArrayFloat.D4 dataArray = (ArrayFloat.D4) dataVar.read();
            System.out.println(dataArray);

            int[] shape = dataArray.getShape();
            System.out.println(shape.getClass());

            OutputStream out = new FileOutputStream(new File("E:\\GitHub\\java-exercises\\src\\main\\java\\com\\wds\\nc\\nc.txt"));
//
            int i = 0;
            for (int time = 0; time < shape[0]; time++) {
                for (int temp = 0; temp < shape[1]; temp++) {
                    for(int lat = 1; lat < shape[2]; lat++) {
                        for (int lon = 0; lon < shape[3]; lon++) {
                            //System.out.println(dataArray.get(time, ground, lat, lon ));
                            String outTxt = String.valueOf(timeArray.getDouble(time)) + ", " + String.valueOf(latArray.getFloat(lat)) + ", " + String.valueOf(lonArray.getFloat(lon)) + ", " + String.valueOf(dataArray.get(time, temp, lat, lon)) + "\n";
                            System.out.print(outTxt);
                            out.write(outTxt.getBytes());
                            i++;
                            if (i == 10) {
                                break;
                            }
                        }
                        if (i == 10) {
                            break;
                        }
                    }
                    if (i == 10) {
                        break;
                    }
                }
            }
            out.flush();
            out.close();
//            int[] origin = new int[2];
//
//            ArrayInt.D2 dataArray;
//
//            dataArray  = (ArrayInt.D2) dataVar.read(origin, shape);
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (InvalidRangeException e) {
//            e.printStackTrace();
        }
    }

}
