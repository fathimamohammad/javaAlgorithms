object File2 extends App{
  def reverseWordsOrder(phrase: String) =
     (phrase.reverse.split(" ", -1) map(_.reverse)).mkString(" ");
  print(reverseWordsOrder("The quick brown fox jumps over the lazy dog"));
}
