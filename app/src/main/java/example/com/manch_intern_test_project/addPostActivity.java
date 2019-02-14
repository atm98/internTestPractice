package example.com.manch_intern_test_project;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import net.alhazmy13.mediapicker.Image.ImagePicker;
import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class addPostActivity extends AppCompatActivity {

    private ImageView imageattachmentView;
    private Button postButton;
    private ConstraintLayout attachmentLayout;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        attachmentLayout = findViewById(R.id.include);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar_layout);
        View v = getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) v.getParent();
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);

        ImageView backButton = v.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(addPostActivity.this,"Back Button Pressed",Toast.LENGTH_LONG).show();
            }
        });

        TextView appBarTitle = v.findViewById(R.id.toolbarTitle);
        appBarTitle.setVisibility(View.VISIBLE);
        appBarTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "hind_bold.ttf"));
        appBarTitle.setText("नया पोस्ट");


        postButton = v.findViewById(R.id.postButton);
        postButton.setBackground(getResources().getDrawable(R.drawable.button_round_background_unselectable));
        postButton.setEnabled(false);
        postButton.setText("आगे बढें");
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(addPostActivity.this,"Post Composed and Submitted",Toast.LENGTH_LONG).show();
            }
        });
        TextView imageText = findViewById(R.id.imageText);
        imageText.setVisibility(View.VISIBLE);
        imageText.setTypeface(Typeface.createFromAsset(this.getAssets(), "hind_light.ttf"));
        imageText.setText("तस्वीर");
        


        TextView cameraText = findViewById(R.id.cameraText);
        cameraText.setVisibility(View.VISIBLE);
        cameraText.setTypeface(Typeface.createFromAsset(this.getAssets(), "hind_light.ttf"));
        cameraText.setText("कैमरा");

        TextView videoText = findViewById(R.id.videoText);
        videoText.setVisibility(View.VISIBLE);
        videoText.setTypeface(Typeface.createFromAsset(this.getAssets(), "hind_light.ttf"));
        videoText.setText("वीडियो");

        TextView attachText = findViewById(R.id.attachText);
        attachText.setVisibility(View.VISIBLE);
        attachText.setTypeface(Typeface.createFromAsset(this.getAssets(), "hind_bold.ttf"));
        attachText.setText("अटैच करें");

        ImageView cameraButton = findViewById(R.id.cameraIcon);
        ImageView imageButton = findViewById(R.id.imageIcon);
        ImageView videoButton = findViewById(R.id.videoIcon);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImagePicker.Builder(addPostActivity.this)
                        .mode(ImagePicker.Mode.CAMERA)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImagePicker.Builder(addPostActivity.this)
                        .mode(ImagePicker.Mode.GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new VideoPicker.Builder(addPostActivity.this)
                        .mode(VideoPicker.Mode.GALLERY)
                        .directory(VideoPicker.Directory.DEFAULT)
                        .extension(VideoPicker.Extension.MP4)
                        .enableDebuggingMode(true)
                        .build();
            }
        });


        EditText postText = findViewById(R.id.postText);
        postText.setHint("अपनी चर्चा या राय यहां लिखें…");
        postText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                postButton.setEnabled(false);
                postButton.setBackground(getResources().getDrawable(R.drawable.button_round_background_unselectable));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    postButton.setEnabled(false);
                    postButton.setBackground(getResources().getDrawable(R.drawable.button_round_background_unselectable));
                }else{
                    postButton.setEnabled(true);
                    postButton.setBackground(getResources().getDrawable(R.drawable.button_round_background));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        imageattachmentView = findViewById(R.id.imageAttachment);
        imageattachmentView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageattachmentView.setVisibility(View.GONE);
                attachmentLayout.setVisibility(View.VISIBLE);
                return true;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths =  data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            //Your Code
            Log.d("path12",mPaths.toString());
            imageattachmentView.setVisibility(View.VISIBLE);
            Glide.with(this).load(Uri.fromFile(new File(mPaths.get(0)))).into(imageattachmentView);
            attachmentLayout.setVisibility(View.GONE);


        }
        else if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            //Your Code
            Log.d("path1",mPaths.toString());
            imageattachmentView.setVisibility(View.VISIBLE);
            Glide.with(this).load(Uri.fromFile(new File(mPaths.get(0)))).into(imageattachmentView);
        }
    }

}
