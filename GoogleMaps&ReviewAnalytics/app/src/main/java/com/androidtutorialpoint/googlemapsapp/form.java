package com.androidtutorialpoint.googlemapsapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

//import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;

/**
 * Created by srihariJoshi on 1/14/2017.
 */

public class form extends Activity {
    private RadioGroup mFirstGroup;
    private RadioGroup mSecondGroup;
    private RadioGroup mThirdGroup;
    private RadioGroup mFourthGroup;
    public int array[]=new int[4];
    private BluetoothDevice device;

/*
    public boolean BTinit()
    {
        boolean found=false;
        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(true)
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Pair the Device first",Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device=iterator;
                    found=true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect()
    {
        boolean connected=true;
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            connected=false;
        }
        if(connected)
        {
            try {
                outputStream=socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream=socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return connected;
    }

           *//* public void onClickStart(View view) {
                if(BTinit())
                {
                    if(BTconnect())
                    {

                    }

                }
            }*/

    void beginListenForData()
    {
        final Handler handler = new Handler();
        stopThread = false;
        buffer = new byte[1024];
        Thread thread  = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopThread)
                {
                    try
                    {
                        int byteCount = inputStream.available();
                        if(byteCount > 0)
                        {
                            threshold=byteCount;
                            thresholdvalues = new byte[byteCount];
                            ldrvalues = new byte[byteCount];
                            inputStream.read(thresholdvalues);
                            inputStream.read(ldrvalues);
                            final String stringthreshold=new String(thresholdvalues,"UTF-8");
                            final String stringldr=new String(ldrvalues,"UTF-8");

                            handler.post(new Runnable() {
                                public void run()
                                {
                                    textView.append(stringthreshold);
                                    textView.append(stringldr);



                                }
                            });

                        }
                    }
                    catch (IOException ex)
                    {
                        stopThread = true;
                    }
                }
            }
        });

        thread.start();
    }

    public OutputStream outputStream;
    private InputStream inputStream;
    Button startButton, sendButton,clearButton,stopButton;
    TextView textView;
    EditText editText;
    boolean deviceConnected=false;
    Thread thread;
    byte buffer[];
    int bufferPosition;
    boolean stopThread;
    //String array1= String.valueOf(new String[3]);
    private final String DEVICE_ADDRESS="20:13:10:15:33:66";
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
    Button button;
//StringBuilder builder;
    private boolean isChecking = true;
    private int mCheckedId = R.id.radioButton6;
    private int mCheckedId1 = R.id.radioButton6;
    private int mCheckedId2 = R.id.radioButton6;
    private int mCheckedId3 = R.id.radioButton6;
    byte[] thresholdvalues;
    byte[] ldrvalues;
    int threshold;
    private static final int REQUEST_ENABLE_BT = 3;
    ArrayList dispositivos;
    BluetoothAdapter adaptador;
    BluetoothDevice dispositivo;
    BluetoothSocket socket;
    InputStream ins;
    OutputStream ons;
    boolean registrado = false;
    int estado;
    String error;
    byte valor;
public double height;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        mFirstGroup = (RadioGroup) findViewById(R.id.Mquestion1);
        mSecondGroup = (RadioGroup) findViewById(R.id.Mquestion2);
        mThirdGroup = (RadioGroup) findViewById(R.id.Mquestion3);
        mFourthGroup = (RadioGroup) findViewById(R.id.Mquestion4);
        mFirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                   // mSecondGroup.clearCheck();
                    mCheckedId = checkedId;
                    array[0]=mCheckedId;
                }
                isChecking = true;
            }
        });
        mSecondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    //mFirstGroup.clearCheck();
                    mCheckedId1 = checkedId;
                    array[1]=mCheckedId1;
                }
                isChecking = true;
            }
        });
        mThirdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    //mFirstGroup.clearCheck();
                    mCheckedId2 = checkedId;
                    array[2]=mCheckedId2;
                }
                isChecking = true;
            }
        });
        mFourthGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                   // mFirstGroup.clearCheck();
                    mCheckedId3 = checkedId;
                    array[3]=mCheckedId3;
                }
                isChecking = true;
            }
        });
        final StringBuilder s = new StringBuilder(4);
        //array1=Arrays.toString(array);
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {



            public void onClick(View arg0) {
                String abc= Arrays.toString(array);
                String answer4=abc.substring(37,47);

                String answer3=abc.substring(25,35);
                String answer2=abc.substring(13,23);
                double a=2.5;
                form obj=new form();

              /*  obj.BTinit();

                obj.BTconnect();*/
                obj.beginListenForData();




                switch(answer4)
                {
                    case "2131624157":height=height+a;

                        break;
                    case "2131624758":height=height-a;

                        break;
                }
                a=a/2;
                double b,c; int i=ldrvalues.length;
                b=0;c=i;
                String answer1=abc.substring(0,11);
                if(!(answer1.equals("2131624139")))
                {
                    switch(answer3)
                    {
                        /*thresholdvalues[]=socket.OutputStream();*/
                        case "2131624151":{b=0;c=i/3;}
                        break;
                        case "2131624152":{b=i/3;c=2*i/3;}
                        break;
                        case "2131624153":{b=2*i/3;c=i;}
                        break;
                    }
                    int j,sumthreshold1=0;
                    for(j=(int)b;j<c;j++)
                    {
                        sumthreshold1+=thresholdvalues[j];
                    }

                    double avgthreshold1=sumthreshold1/(c-b);
                    int h=thresholdvalues.length;
                    switch(answer2)
                    {
                       // ldrvalues[]=socket.OutputStream();
                        case "2131624146":{b=0;c=h/3;}
                        break;
                        case "2131624147":{b=h/3;c=2*h/3;}
                        break;
                        case "2131624148":{b=2*h/3;c=h;}
                        break;
                    }
                    int k,sumthreshold2=0,avgthreshold2=0;
                    for(k=(int)b;j<c;j++)
                    {
                        if((ldrvalues[k]<threshold) && (ldrvalues[k]>ldrvalues[k+1]))
                        {
                            sumthreshold2+=ldrvalues[k];
                        }
                        avgthreshold2=sumthreshold2/(int)(c-b);

               // button.setText(array);

            }
                    Intent intent=new Intent(form.this,MapsActivity.class);
                    startActivity(intent);
        } Intent intent=new Intent(form.this,MapsActivity.class);
                startActivity(intent);
        };

    });

        }}


   /* public void showType(View view) {
        if (mCheckedId == R.id.radioButton6) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radioButton7) {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radioButton3) {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radioButton4) {
            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
        }*//*else if (mCheckedId == R.id.radioButton5) {
            Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
        } else if (mCheckedId == R.id.radioButton8) {
            Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
        }*//*
    }*/
