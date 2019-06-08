package com.example.arpit.cryptomarket;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.arpit.cryptomarket.Adapter.CoinAdapter;
import com.example.arpit.cryptomarket.Fragments.AboutFragment;
import com.example.arpit.cryptomarket.Fragments.FavouriteFragment;
import com.example.arpit.cryptomarket.Interface.ILoadMore;
import com.example.arpit.cryptomarket.Model.CoinModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    List<CoinModel> items = new ArrayList<>();
    CoinAdapter adapter;
    RecyclerView recyclerView;
    OkHttpClient client;
    Request request;
    SwipeRefreshLayout swipeRefreshLayout;
    Drawer result;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        swipeRefreshLayout = findViewById(R.id.rootLayout);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10coin(0);
            }
        });

        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("CryptoMarket");
        firebaseAuth = FirebaseAuth.getInstance();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Arpit Jindal")
                                .withEmail("arpitjindaldtu@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profile2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home")
                .withIcon(FontAwesome.Icon.faw_chart_line);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Favourites")
                .withIcon(FontAwesome.Icon.faw_heart);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(4).withName("About")
                .withIcon(FontAwesome.Icon.faw_github);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(5).withName("Logout")
                .withIcon(FontAwesome.Icon.faw_power_off);


        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems( item1, item2,
                        new DividerDrawerItem(),
                        item3,item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch(position){
                            case 1:
                                startActivity(new Intent(getBaseContext(),HomeActivity.class));
                                result.closeDrawer();
                                break;
                            case 2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                        new FavouriteFragment()).commit();
                                swipeRefreshLayout.setVisibility(View.INVISIBLE);
                                result.closeDrawer();
                                break;
                            case 4:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                        new AboutFragment()).commit();
                                swipeRefreshLayout.setVisibility(View.INVISIBLE);
                                result.closeDrawer();
                                break;
                            case 5:
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                                break;
                        }

                        return true;
                    }
                })
                .build();
        result.addStickyFooterItem(new PrimaryDrawerItem().withName("CryptoMarket"));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10coin(0);
                setupAdapter();
            }
        });

        recyclerView = findViewById(R.id.coinList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new CoinAdapter(recyclerView,HomeActivity.this,items);
        recyclerView.setAdapter(adapter);
        adapter.setiLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size() <= 1000) //Max size is 1000 coin
                {
                    loadNext10coin(items.size());
                }else{
                    Toast.makeText(HomeActivity.this, "Max Items is 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNext10coin(int size) {
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",size))
                .build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                final List<CoinModel> newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>(){}.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(newItems);
                        adapter.setLoader();
                        adapter.updateData(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void loadFirst10coin(int i) {
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",i))
                .build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                HomeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HomeActivity.this,"ERROR!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                Gson gson = new Gson();
                final List<CoinModel> newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>(){}.getType());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(newItems);

                    }
                });
            }
        });
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }
}
