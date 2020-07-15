package com.example.formgenerator.utils;

import android.app.Application;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }





    private void createPdfDoc() {

        PdfDocument pdfDocument = new PdfDocument();
        Paint mPaint = new Paint();
        PdfDocument.PageInfo mPageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page mPage = pdfDocument.startPage(mPageInfo);
        Canvas canvas = mPage.getCanvas();
        //canvas.drawText(sessionManager.getUserName(),125, 10, mPaint);
        canvas.drawText("pdf test", 40, 50, mPaint);
       // String problem = binding.problems.getText().toString();
        //canvas.drawText(problem, 40, 100, mPaint);
        String datee =  android.text.format.DateFormat.format("yyyy-MM-dd hh:mm", new java.util.Date()).toString();
        canvas.drawText(datee, 40, 150, mPaint);
        //File file = new File(Environment.getDataDirectory(), File.separator + "myFuckingPdf.pdf");
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),File.separator+ "test.pdf");
        pdfDocument.finishPage(mPage);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Log.e("TAG", "createPdfDoc: "+ file.getPath() );
            pdfDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        startActivity(intent);

    }
}
