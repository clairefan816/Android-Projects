package claire.app.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar superProgressBar;
    ImageView superImageView;
    WebView superWebView;
    LinearLayout mylinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        superImageView = (ImageView) findViewById(R.id.myImageView);
        superWebView = (WebView) findViewById(R.id.myWebView);
        mylinearLayout = (LinearLayout) findViewById(R.id.myLinearLayout);

        superProgressBar.setMax(100);
        superWebView.loadUrl("https:www.google.com");
        superWebView.getSettings().setJavaScriptEnabled(true);

        superWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mylinearLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mylinearLayout.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        superWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superProgressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superImageView.setImageBitmap(icon);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        //different layout here
        menuInflater.inflate(R.menu.super_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // add click listener
        switch(item.getItemId()){
            case R.id.menu_back:
                onBackPressed();
                break;
            case R.id.menu_forward:
                onForwardPressed();
                break;
            case R.id.menu_refresh:
                superWebView.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onForwardPressed(){
        if (superWebView.canGoForward()){
            superWebView.goForward();
        } else {
            Toast.makeText(this, "Cannot go forward!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed(){
        if (superWebView.canGoBack()){
            superWebView.goBack();
        } else {
            finish();
        }
    }
}