# Uno Game with AI and Custom Player Options

## Overview

This project is an implementation of the popular card game **Uno** in Java, with additional features like AI opponents and various player options. The game allows users to play against both AI and human players, with customizable rules and settings.

## Features

- **Single-player mode**: Play against AI opponents with varying difficulty levels.
- **Multiplayer mode**: Play with friends locally.
- **Customizable game settings**: Choose the number of players, AI difficulty, and other game rules.
- **Graphical User Interface (GUI)**: A user-friendly interface for interacting with the game.
- **ArrayBag Class Integration**: Used for managing the collection of cards, providing efficient and dynamic handling of the game deck.

Easy AI: Makes random moves.
Medium AI: Plays with basic strategy, considering the current game state.
Hard AI: Uses advanced strategies to anticipate future moves and block opponents.
ArrayBag Class Usage
The ArrayBag class is used to manage the deck and each player's hand. It provides a flexible and efficient way to add, remove, and check for cards, improving the overall performance and structure of the game.

Key Methods:
add(Object item): Adds a card to the bag.
remove(Object item): Removes a card from the bag.
contains(Object item): Checks if a card is in the bag.
toArray(): Converts the bag's contents into an array for easy access.
Future Enhancements
Online Multiplayer: Allow players to compete over the internet.
Additional AI Personalities: Implement different AI behaviors to add more variety to the gameplay.
Customizable Card Decks: Introduce themed decks or allow users to create their own.
Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your improvements.
