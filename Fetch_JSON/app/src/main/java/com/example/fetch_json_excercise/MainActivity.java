package com.example.fetch_json_excercise;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    //JSON LINK
    public static String JSON_LINK = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    List<List_Model> the_list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        the_list = new ArrayList<List_Model>();
        recyclerView = findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute();

    }

    public void PutDataIntoRecyclerView(List<List_Model> listModel) {
        Adaptery adaptery = new Adaptery(this, listModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptery);
    }


    public class GetData extends AsyncTask<Integer, Integer, String> {


        @Override
        protected String doInBackground(Integer... integers) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_LINK);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }

                    //System.out.println("Im here : =" + current);

                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            List<List_Model> group1, group2, group3, group4;
            group1 = new ArrayList<List_Model>();
            group2 = new ArrayList<List_Model>();
            group3 = new ArrayList<List_Model>();
            group4 = new ArrayList<List_Model>();

            try {
                //System.out.println("IM HEEERRE:: ====" + s);

                JSONArray data = new JSONArray(s);

                for (int i = 0; i < data.length(); i++) {

                    JSONObject obj = data.getJSONObject(i);

                    List_Model model = new List_Model();
                    model.setId(obj.getInt("id"));
                    model.setListID(obj.getInt("listId"));
                    model.setName(obj.getString("name"));


                    // ONLY ADD ITEMS TO the_list THAT ARE NOT NULL or EMPTY
                    // GROUPING BY listID
                    // ----
                    if (!model.getName().equals("null") && !model.getName().equals("") && model.getListID() == 1) {
                        group1.add(model);
                    } else if (!model.getName().equals("null") && !model.getName().equals("") && model.getListID() == 2) {
                        group2.add(model);
                    } else if (!model.getName().equals("null") && !model.getName().equals("") && model.getListID() == 3) {
                        group3.add(model);
                    } else if (!model.getName().equals("null") && !model.getName().equals("") && model.getListID() == 4) {
                        group4.add(model);
                    }

                    List<List_Model> newList = Stream.of(group1, group2, group3, group4)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());

                    the_list = newList;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(the_list);

        }

    }

}
