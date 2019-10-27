package com.eventmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventmanager.R;
import com.eventmanager.databinding.NoteItemBinding;
import com.eventmanager.model.entity.Note;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    //private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.note_item, parent, false);
//        return new NoteHolder(itemView);


        NoteItemBinding noteItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.note_item, parent, false);
        return new NoteHolder(noteItemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.noteItemBinding.setNote(currentNote);
    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }

//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private NoteItemBinding noteItemBinding;

        public NoteHolder(@NonNull NoteItemBinding noteItemBinding) {
            super(noteItemBinding.getRoot());
            this.noteItemBinding = noteItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
