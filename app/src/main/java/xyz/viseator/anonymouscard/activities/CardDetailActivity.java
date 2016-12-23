package xyz.viseator.anonymouscard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import xyz.viseator.anonymouscard.R;
import xyz.viseator.anonymouscard.data.ConvertData;
import xyz.viseator.anonymouscard.data.DataPackage;
import xyz.viseator.anonymouscard.network.SingleUtil;

public class CardDetailActivity extends AppCompatActivity {
    private SingleUtil singleUtil;
    private DataPackage dataPackage;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == SingleUtil.SINGLE_PORT) {
                dataPackage = (DataPackage) (ConvertData.byteToObject((byte[]) msg.obj));
                Intent intent = new Intent();
                intent.putExtra("data", dataPackage);
                setResult(RESULT_OK, intent);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
    }

    private void init() {
        singleUtil = new SingleUtil(handler);
        singleUtil.startRecieveMsg();

    }
}
