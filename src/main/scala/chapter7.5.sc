// 7.5 Enriched Interfaces
implicit class ExtraStringMethods(str: String) {
  val vowels = Seq('a', 'e', 'i', 'o', 'u')

  def numberOfVowels =
    str.toList.filter(vowels contains _).length
}

new ExtraStringMethods("the quick brown fox").numberOfVowels

"the quick brown fox".numberOfVowels