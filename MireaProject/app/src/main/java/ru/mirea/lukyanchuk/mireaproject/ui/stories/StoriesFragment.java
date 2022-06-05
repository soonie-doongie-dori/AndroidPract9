package ru.mirea.lukyanchuk.mireaproject.ui.stories;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import ru.mirea.lukyanchuk.mireaproject.R;
import ru.mirea.lukyanchuk.mireaproject.ui.App;


public class StoriesFragment extends Fragment {

    static int num =0;
    private List<Story> stories;
    private RecyclerView recyclerView;
    private Button newStory;
    private StoriesDAO storiesDAO;
    private AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = App.getInstance().getDatabase();

        storiesDAO = db.storiesDAO();
        stories = storiesDAO.getAll();

        Log.d("xyq", String.valueOf(stories.size()));
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        for (Story it: stories){
            Log.d("StoriesFragment", it.number);
        }
        newStory = view.findViewById(R.id.button);
        newStory.setOnClickListener(this::onClick);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        StoryAdapter adapter = new StoryAdapter(stories);
        recyclerView.setAdapter(adapter);

        return view;
    }


    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), StoriesView.class);
        startActivity(intent);

    }
}