/*
Java program that manages a simple playlist for a media player.
The program is able to add songs to the playlist, remove songs by title, and display the current playlist.
Each song has a title and a duration. Binary search tree stores the songs, where each node represents a song.
There is a method to play the next song in alphabetical order and mark it as played.
Insurance of the thread safety when adding or removing songs.
 */


package com.kukharev.core.javaCore.PlaylistManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Song implements Comparable<Song> {
    private final String title;
    private final double duration;
    private boolean played;
    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
        this.played = false;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    @Override
    public int compareTo(Song other) {
        return this.title.compareTo(other.title);
    }
}

class PlaylistManager {
    private BSTNode root;
    private final Lock lock = new ReentrantLock();
    public void addSong(Song song) {
        lock.lock();
        try {
            root = addRecursive(root, song);
        } finally {
            lock.unlock();
        }
    }

    private BSTNode addRecursive(BSTNode current, Song song) {
        if (current == null) {
            return new BSTNode(song);
        }
        if (song.compareTo(current.song) < 0) {
            current.left = addRecursive(current.left, song);
        } else if (song.compareTo(current.song) > 0) {
            current.right = addRecursive(current.right, song);
        }
        return current;
    }

    public void removeSong(String title) {
        lock.lock();
        try {
            root = removeRecursive(root, title);
        } finally {
            lock.unlock();
        }
    }

    private BSTNode removeRecursive(BSTNode current, String title) {
        if (current == null) {
            return null;
        }
        if (title.equals(current.song.getTitle())) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            Song smallestSong = findSmallestSong(current.right);
            current.song = smallestSong;
            current.right = removeRecursive(current.right, smallestSong.getTitle());
            return current;
        }
        if (title.compareTo(current.song.getTitle()) < 0) {
            current.left = removeRecursive(current.left, title);
        } else {
            current.right = removeRecursive(current.right, title);
        }
        return current;
    }

    private Song findSmallestSong(BSTNode root) {
        return root.left == null ? root.song : findSmallestSong(root.left);
    }

    public Song playNextSong() {
        lock.lock();
        try {
            BSTNode node = findNextSong(root);
            if (node != null) {
                node.song.setPlayed(true);
                return node.song;
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    private BSTNode findNextSong(BSTNode current) {
        if (current == null) {
            return null;
        }
        if (current.left != null) {
            BSTNode leftNode = findNextSong(current.left);
            if (leftNode != null) {
                return leftNode;
            }
        }
        if (!current.song.isPlayed()) {
            return current;
        }
        return findNextSong(current.right);
    }

    public String displayPlaylist() {
        StringBuilder sb = new StringBuilder();
        displayRecursive(root, sb);
        return sb.toString();
    }

    private void displayRecursive(BSTNode current, StringBuilder sb) {
        if (current != null) {
            displayRecursive(current.left, sb);
            sb.append(current.song.getTitle())
                    .append(" - ")
                    .append(current.song.getDuration())
                    .append(current.song.isPlayed() ? " (Played)\n" : "\n");
            displayRecursive(current.right, sb);
        }
    }

    private static class BSTNode {
        Song song;
        BSTNode left;
        BSTNode right;
        BSTNode(Song song) {
            this.song = song;
        }
    }
}