import model.User;
import model.ListUsers;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.ResponseUser;
import services.ResponseUsersList;
import services.RetrofitUsersService;
import services.RetrofitUsersServiceListUsers;

import java.io.*;
import java.util.*;

import java.util.ArrayList;
public class MainTest {

    public static void main(String args[]){

        Properties prop = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream("C:\\Users\\Christian\\Desktop\\UTN\\DDS\\API\\retrofit-example\\src\\main\\java\\ConfigTest.properties");
            prop.load(is);
        } catch(IOException e) {
            System.out.println(e.toString());
        }

        System.out.println("Levantando Archivo de Configuración:");
        System.out.println("-----------------------");
        System.out.println(prop.getProperty("URL_PATH"));
        System.out.println(prop.getProperty("variable"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(prop.getProperty("URL_PATH"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitUsersService service = retrofit.create(RetrofitUsersService.class);

     /*   Call<ResponseUser> call = service.getUser(3);
        try{
            Response<ResponseUser> response = call.execute();
            User user = response.body().data;

            System.out.print(user.allData());
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }
*/
        RetrofitUsersServiceListUsers serviceListUsers = retrofit.create(RetrofitUsersServiceListUsers.class);

        Call<ResponseUsersList> callListUsers = serviceListUsers.getUserList(2);

        try{
            Response<ResponseUsersList> responsceListUsers = callListUsers.execute();
            int page = responsceListUsers.body().page;
            ArrayList<User> users= responsceListUsers.body().data;

            System.out.printf("MOSTRANDO USUARIOS DE PAGINA: %d \n", page);
            System.out.printf("");

            for (User obj : users) {
                System.out.println(obj.allData());
                System.out.println("");
            }


        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }



    }
}
