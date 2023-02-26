=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: agsaanvi
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. My first concept was collections. I used collections in a few different ways.
  I used an arrayList to store all the possible words my game has. In other
  words, it stored my word bank. The word bank arraylist was used to verify that
  every guess entered by the user was considered valid. I believe this was an
  appropriate use of the concept since having an arraylist allowed me to easily
  add more words to my word bank or switch to a different file while coding
  since an arraylist is mutable. It also allowed me to check the words easily
  using a contains method. Lastly, an arraylist can be easily generated through
  reading a file. The file reader's default for reading all lines is an arraylist
  so I didn't have to add complicated or unnecessary casting to make it work. I
  also used collections with storing my past guesses for the load and save files.
  This was done for convenience since loading into an arraylist is easier than other
  data structures and since the arraylist is mutable that means the file does not
  load extra spaces for no reason and only stores the actual information. This part
  was done to earn credit for the section but simply for ease of programming. Its
  the word verification that fulfills the rubric's requirements.

  2. The second concept was File I/O. File I/O was used to save the game progress
  and load the game progress. The way it works is that the game loads the guesses
  and the word of the current round. It can load them directly onto the interface
  so the player can keep playing the game. This is an appropriate use of the
  concept because its only through storing the data in the file that we can
  guarantee that the game will be saved even when the game is closed. A file will
  remain even after the game is closed or even if everything is reset. Its separate
  from everything else like the gui and code, so it works. Moreover, the implementation
  fulfills all the requirements of the rubric. The load and save methods check if the
  file is null or if the data is null. It passes through a valid situation for all of
  those cases. When its null, its simply resets the game and says so in the status.
  Moreover, the data stored is enough to basically recreate the game at where it
  stopped and that is what it does. It stores the past guesses and shows their
  colors on the interface. Lastly, it reads and writes to the file correctly and
  as needed.

  3. The third concept is 2D array. I use one 2D array in my gameBoard class to
  store and showcase all the past guesses and create the interface. The array
  stores the past guesses' letters and colors as a JLabel and then I call upon
  it to create the grid interface of my game. The use of having an array is that
  it isn't mutable in size which is needed since its always going to be a 6 by 5
  grid. Moreover, it allows access to every cell and allows segregation by both
  rows and columns which is needed to iterate over different sections throughout
  the game. For example, the actual colors for each guess is calculated a row at
  a time and it iterates over the columns so its helpful to only access that row.
  I also changed this from my proposal based on my TA's feedback to have user
  input be stored in there. Lastly, if another data structure was used, it
  wouldn't be possible to break apart the data and run most of the methods. It
  would also be difficult to create the one guess insert at a time since there
  wouldn't be rows in a collection to separate the columns.

  4. The fourth concept is JUnit Testable Component. This was implemented by
  encapsulating the Wordle class from the other two classes. Essentially, the
  Wordle class can run independent of the all the code for the gui. The Wordle
  class can run a Wordle game on the terminal using a printGameState method.
  This fact has allowed me to create a test case class that tests every method
  in that class. I was able to test the game logic and I ran the Wordle game in
  the terminal until I had everything debugged before building the Gui. By
  implementing this, I was able to test the game logic which wasn't possible
  with other coding methods. Moreover, it took into account the feedback on my
  proposal which recommended switching one of my concepts to this. Lastly, it
  fulfills the rubric's requirements by having many cases including multiple
  edge cases and not relying on the gui.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  I have 3 classes in my game: Wordle, RunWordle, and GameBoard.
  I also technically have a 4th class called WordleTests, which
  tests everything from the Wordle class to make sure its
  working.

  The Wordle class implements all the game logic. This is the part that
  actually codes the methods that check the guesses and whether they are
  valid inputs. It also then figures out the colors for the letters
  after comapring them to the actual word. It also stores all the game
  information like the word bank and the actual word. Following the
  MOD structure, this would be the model.

  The GameBoard class builds the main panel of the interface and creates
  a visual representation or a gui version of the game logic. It takes the
  outputs from the Wordle class and puts it onto the screen. It displays
  the game logic in a way that the user can actually understand. In the
  MOD structure, this would be the view.

  The RunWordle class is the part that interacts with the user and takes
  into account their actions. It accepts the inputs in the text field
  and converts them into strings and values that can be used by the
  Wordle and GameBoard classes to create changes in the game. In the
  MOD structure, this would be the controller.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  One of my biggest challenges was implementing the GUI. I wrote Wordle first
  and I found that fairly simple since the logic was easy once I started
  adding test cases and writing out what each method should do. However, it
  was difficult to figure out how to get the user inputs and values as
  needed for the GUI. I struggled with creating a text field and implementing
  it. I also didn't know how to create the proper layouts or use JPanels
  well until I started programming so it took multiple tries. My other stumble
  was that my load method took a lot of time to work out. I found that the
  method would load more than the required information or load copies. I
  eventually added the parameter of the attempts to limit this but it
  required multiple changes.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  My game has a strong separation of functionality. None of the gui methods or
  information is in the Wordle class. If the gui files were deleted. The
  Wordle class would still work. Moreover, any information in the Wordle
  class is only accessed through getters and setter methods in the GUI.
  Its completely encapsulated. If I could do this again, I think I
  would change my way of implementing colors and game modes. I think there
  is probabaly a simpler method of storing all this information like
  whether the player has won or lost or if the word is valid. I would
  want to recreate it.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

    Original Wordle Game: https://www.nytimes.com/games/wordle/index.html
    Word Bank Website: https://7esl.com/5-letter-words/
    Some of the java docs that I used:
    https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
    https://docs.oracle.com/javase/7/docs/api/javax/swing/JLabel.html
    https://docs.oracle.com/javase/7/docs/api/javax/swing/JDialog.html
    https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextField.html
