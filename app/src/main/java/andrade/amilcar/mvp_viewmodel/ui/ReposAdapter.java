package andrade.amilcar.mvp_viewmodel.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import andrade.amilcar.mvp_viewmodel.R;
import andrade.amilcar.mvp_viewmodel.model.Repo;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    private final List<Repo> items;

    public ReposAdapter(List<Repo> items) {
        this.items = items;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        RepoViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }

        void bind(@NonNull Repo repo) {
            title.setText(repo.getName());
            description.setText(repo.getDescription());
        }
    }
}
