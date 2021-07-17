package com.musafi.bookslist.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class MyTextManager {

    /**
     * Search for a match between the matching text and the tow other text. If there is a match it will change the background color of the subtext
     * @param context The context of the current state of the application
     * @param first_textView The first text check
     * @param second_textView The second text check
     * @param matching_text The matching text
     * @param markerColor The color of the marker(background color of the subString)
     */
    public static void highlightMatchText(Context context,TextView first_textView, TextView second_textView, String matching_text, int markerColor){
        int name_start_index = first_textView.getText().toString().toLowerCase().indexOf(matching_text.toLowerCase());
        int author_start_index = second_textView.getText().toString().toLowerCase().indexOf(matching_text.toLowerCase());
        if(name_start_index > -1 && !matching_text.isEmpty()) {
            highlightTextPart(context, first_textView, name_start_index, name_start_index + matching_text.length(), markerColor);
        }
        if(author_start_index > -1 && !matching_text.isEmpty()) {
            highlightTextPart(context, second_textView, author_start_index, author_start_index + matching_text.length(), markerColor);
        }
    }

    /**
     * Change the background color of a subtext
     * @param context The context of the current state of the application
     * @param textView The target textView
     * @param startPos The start point of the marker
     * @param endPos The end point of the marker
     * @param markerColor The color of the marker
     */
    private static void highlightTextPart(Context context, TextView textView, int startPos, int endPos, int markerColor) {
        String fullText = textView.getText().toString();
        Spannable spannable = new SpannableString(fullText);
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(ContextCompat.getColor(context, markerColor));
        spannable.setSpan(backgroundColorSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }
}
