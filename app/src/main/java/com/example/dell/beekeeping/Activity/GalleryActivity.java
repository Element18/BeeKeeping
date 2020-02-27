package com.example.dell.beekeeping.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.MemoryFile;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.beekeeping.Adapter.Image_Adapter;
import com.example.dell.beekeeping.DataBase.DbOpenHelper;
import com.example.dell.beekeeping.DataBase.Image_Contract;
import com.example.dell.beekeeping.Models.Image_Model;
import com.example.dell.beekeeping.R;

import java.io.File;
import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    ImageView imageView;
    private File photoFile = null;
    private int REQUEST_CAMERA = 123;
    private Integer SELECT_FILE = 0;
    private String mCurrentPhoto = "";
    EditText editText;
    DbOpenHelper db;
    Image_Model image_model;
    Image_Adapter image_adapter;
    GridView gridView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        fab = findViewById(R.id.fab3);
        db = new DbOpenHelper(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            }

        } else {
            displayMessage(this, "this app is not going  to work without camera permission");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void displayMessage (Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void selectImage () {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        final  View view = layoutInflater.inflate(R.layout.gallery_alert_layout,null);
        builder.setView(view);
        this.imageView = (ImageView) view.findViewById(R.id.imageView2);
        this.editText = (EditText) view.findViewById(R.id.addCaption);
        Button btnCamera = view.findViewById(R.id.camera);
        Button btngallery = view.findViewById(R.id.gallery);
        Button btnvideo = view.findViewById(R.id.video);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager())!= null)
                {

                    startActivityForResult(intent,REQUEST_CAMERA);
                }
            }
        });

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Pictures"),SELECT_FILE);
            }
        });
        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {

                String gottenText = editText.getText().toString();
                Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                AddToView(gottenText,image);
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void AddToView(String caption, Bitmap bitmap){
        Image_Model image_model = new Image_Model(caption,bitmap);
        boolean data_inserted = db.addToMemory(image_model);

        if (data_inserted)
            Toast.makeText(this, "data is Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "data  not inserted", Toast.LENGTH_LONG).show();

        this.gridView = (GridView)findViewById(R.id.gridView);
        this.gridView.setAdapter(new Image_Adapter(this,this.db.readAllMemory(),false));
        LinearLayoutManager llm = new LinearLayoutManager(this);

    }
//
//    @Override
//    protected void onResume () {
//        super.onResume();
//        ((CursorAdapter)gridView.getAdapter()).swapCursor(this.db.readAllMemory());
//    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                Bitmap imageBitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(imageBitmap);


            } else if (requestCode == SELECT_FILE) {

                try {
                    Uri selectedImage = data.getData();
                    InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                    imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));

                }catch (Exception e){
                    e.printStackTrace();
                }

//                imageView.setImageURI(selectedImage);
            }
        }

    }

}
