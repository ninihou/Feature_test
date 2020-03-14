import java.sql.*;
import java.io.*;
//ji ji j ij ij ij iji j
class PhotoWrite {
  
        static {
            try {
                 Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }

        public static void main(String args[]) {
              Connection con = null;
              PreparedStatement pstmt = null;
              String url = "jdbc:oracle:thin:@localhost:49161:XE";
              String userid = "DA106";
              String passwd = "123456";
              //String picName = "back1.gif";
	        
              try {
                con = DriverManager.getConnection(url, userid, passwd);
                File pic = new File("picFrom", picName); //�۹���|- picFrom
                                                         //������|- Ĵ�p:
                                                         //File pic = new File("x:\\aa\\bb\\picFrom", picName);
                long flen = pic.length();
                String fileName = pic.getName();
                int dotPos = fileName.indexOf('.');
                String fno = fileName.substring(0, dotPos);
                String format = fileName.substring(dotPos + 1);
                InputStream fin = new FileInputStream(pic);  

                System.out.println("\n\nUpdate the database... ");
                pstmt = con.prepareStatement(
                 "insert into LIVESTREAM (PICTURE, TITLE, INTRODUCTION)  values(?, ?, ?)");
                pstmt.setString(6, fno);
                pstmt.setString(8, format);
                pstmt.setBinaryStream(7, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
                int rowsUpdated = pstmt.executeUpdate();
			
                System.out.print("Changed " + rowsUpdated);

                if (1 == rowsUpdated)
                    System.out.println(" row.");
                else
                    System.out.println(" rows.");

                fin.close();
                pstmt.close();

              } catch (Exception e) {
                    e.printStackTrace();
              } finally {
                    try {
                      con.close();
                    } catch (SQLException e) {
                    }
             }
      }
}
