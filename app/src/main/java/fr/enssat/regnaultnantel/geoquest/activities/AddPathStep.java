package fr.enssat.regnaultnantel.geoquest.activities;

import android.os.Bundle;
import fr.enssat.regnaultnantel.geoquest.R;

@Deprecated
public class AddPathStep extends AbstractGeoQuestActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
    }

//    private Button mSaveButton;
//    private Button mTakePictureButton;
//    private Button mDelPictureButton;
//    private ImageView mImageView;
//    private Intent mCameraIntent;
//    private Bitmap mHintPicture;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_path_step);
//        mHintPicture = null;
//
//        mCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        mSaveButton = (Button) findViewById(R.id.save_new_step);
//        mImageView = (ImageView) findViewById(R.id.photo_indice);
//        mTakePictureButton = (Button) findViewById(R.id.take_picture);
//
//        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                takePicture(v);
//            }
//        });
//
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveBeaconAndQuit();
//            }
//        });
//
//    }
//
//    public void takePicture(View v) {
//        startActivityForResult(mCameraIntent, 42);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 42 && resultCode != 0) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            mHintPicture = photo;
//            /**
//             ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//             byte[] b = baos.toByteArray();
//             String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
//
//             System.out.println(encodedImage);
//
//             byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
//             Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//             mImageView.setImageBitmap(decodedByte);**/
//        }
//    }
//
//    private void saveBeaconAndQuit() {
//
//    }

}
