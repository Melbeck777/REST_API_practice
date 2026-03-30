Feature: Books API Test

  Background:
    * url baseUrl

  Scenario: Get all books
    Given path '/books'
    When method get
    Then status 200
    And match each response ==
      """
      {
        id: 1,
        title: '#string',
        author: '#string',
        publishDate: '#string',
        price: '#number',
        publisher: '#string'
      }
      """

  Scenario: Get a specific book by ID
    Given path '/books/1'
    When method get
    Then status 200
    And match response ==
      """
      {
        id: 1,
        title: '#string',
        author: '#string',
        publishDate: '#string',
        price: '#number',
        publisher: '#string'
      }
      """

  Scenario: Get a non-existent book
    Given path '/books/9999'
    When method get
    Then status 404
