package com.mob.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView contextMenu;

    Button popupMenuButton;
    Button showPopupMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textContent);

        contextMenu = (TextView) findViewById(R.id.contextMenu);
        registerForContextMenu(contextMenu);

        popupMenuButton = findViewById(R.id.popupMenu);
        showPopupMenuButton = findViewById(R.id.showPopupMenu);
        showPopupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                textView.setText("popup menu clicked");
                popupMenuClicked();
            }
        });

    }

    private void popupMenuClicked() {
        PopupMenu popupMenu = new PopupMenu(this, popupMenuButton);

        popupMenu.getMenuInflater().inflate(R.menu.context_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.item1) {
                    textView.setText("popup menu: item1");
                    return true;
                } else if (itemId == R.id.item2) {
                    textView.setText("popup menu: item2");
                    return true;
                } else if (itemId == R.id.item3) {
                    textView.setText("popup menu: item3");
                    return true;
                } else if (itemId == R.id.item4) {
                    textView.setText("popup menu: item4");
                    return true;
                }
                return false;

            }


        });

        popupMenu.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            textView.setText("context menu: item1");
            return true;
        } else if (itemId == R.id.item2) {
            textView.setText("context menu: item2");
            return true;
        } else if (itemId == R.id.item3) {
            textView.setText("context menu: item3");
            return true;
        } else if (itemId == R.id.item4) {
            textView.setText("context menu: item4");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setting) {
            textView.setText("option menu: setting");
            return true;
        }

        if (id == R.id.submenu) {
            textView.setText("option menu:about");
            return true;
        }

        if (id == R.id.subItem1) {
            textView.setText("option menu:item1");
            return true;
        }

        if (id == R.id.subItem2) {
            textView.setText("option menu:item2");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}