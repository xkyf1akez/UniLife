/*
//copied from https://developer.android.com/reference/android/widget/ListView.html#ListView(android.content.Context,%20android.util.AttributeSet)
package integratedproject.unilife_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

private class MyAdapter extends BaseAdapter {

      // override other abstract methods here

      @Override
      public View getView(int position, View convertView, ViewGroup container) {
          if (convertView == null) {
              convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
          }

          ((TextView) convertView.findViewById(android.R.id.text1))
                  .setText(getItem(position));
          return convertView;
      }
  }
*/