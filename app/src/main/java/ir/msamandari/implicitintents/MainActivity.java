package ir.msamandari.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lord-ImplicitIntent";
    private EditText mWebsiteEditText, mLocationEditText, mShareTextEditText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite (View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);

        // In this constructor you specify an action and the data for that action. Actions are
        // defined by the Intent class and can include ACTION_VIEW (to view the given data),
        // ACTION_EDIT (to edit the given data), or ACTION_DIAL (to dial a phone number).
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Use the resolveActivity() method and the Android package manager to find an Activity that
        // can handle your implicit Intent. Make sure that the request resolved successfully.
        //This request that matches your Intent action and data with the Intent filters for installed
        // apps on the device. You use it to make sure there is at least one Activity that can handle your requests.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "openWebsite: ImplicitIntent : Can't handle this");
        }
    }

    public void openLocation (View view) {
        String location = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "openLocation: Location Not Found.");
        }
    }

    public void shareText (View view) {
        String txt = mShareTextEditText.getText().toString();

        //Defining the mime type of the text to share.
        String mimeType = "text/plain";

        //        A share action is an easy way for users to share items in your app with social networks
        //        and other apps. Although you could build a share action in your own app using an implicit
        //        Intent, Android provides the ShareCompat.IntentBuilder helper class to make implementing
        //        sharing easy. You can use ShareCompat.IntentBuilder to build an Intent and launch a chooser
        //        to let the user choose the destination app for sharing.
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }

    public void capturePicture (View view) {
        //Opening Camera To Capture a Picture
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}
