package com.example.panicbutton.views;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.panicbutton.R;

/**
 * Implementation of App Widget functionality.
 */
public class PanicWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, PanicActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.panic_widget);
            views.setOnClickPendingIntent(R.id.panicWidget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}