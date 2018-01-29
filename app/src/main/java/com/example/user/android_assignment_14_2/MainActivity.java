package com.example.user.android_assignment_14_2;
//Package objects contain version information about the implementation and specification of a Java package.
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//public is a method and fields can be accessed by the members of any class.
//class is a collections of objects.
//created MainActivity and extends with AppCompatActivity which is Parent class.

    ImageView imageview;
    Button button;
    //Intializing the imageview and button.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        imageview=(ImageView)findViewById(R.id.imageView);
        //giving find view by id as image view
        button=(Button)findViewById(R.id.button);
        //giving find view by id as button
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //giving permission to manifest file to grant to write external storage
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            //giving request code to the string
        }
        final ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        //this class is used to write common data into multiple files
        button.setOnClickListener(new View.OnClickListener() {
            //giving set on click listner to the button
            @Override
            public void onClick(View view) {
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.image);
                //getting the image from drawable folder
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
                //bit map is a mapping from some domain to bits.
                bitmap.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
                // finding SD Card path
                File filepath=new File(Environment.getExternalStorageDirectory()+"/Image File/");
                // try & catch block
                try {
                    Log.e("path", "path= "+ new File( Environment.getExternalStorageDirectory()
                            + "/SampleImage.png").getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    //creating a new file
                    filepath.createNewFile();
                    //creating object of fileoutputstream
                    FileOutputStream fileOutputStream=new FileOutputStream(filepath);
                    //converting the data into byte
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    //closing the file
                    fileOutputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                //toasting giving as image saved succesfully when we click on the save button

            }
        });

    }

}

