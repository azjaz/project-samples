Feature: Menu screen
  This feature deals with menu screen of Papajohns site

  Background:
    Given The user opens Papajohns site
    And The user switches to English version

  Scenario Outline: correspondence between pizza size and people amount
    When The user chooses <Pizza size> of <Pizza name>
    Then The user sees <Persons number> for selected size of <Pizza name>

    Examples:
      | Pizza name | Pizza size | Persons number |
      | Greek      | 23 cm      | 1              |
      | Pepperoni  | 30         | 1-2            |
      | Vegetarian | 35         | 2-3            |
      | 4 Cheese   | 40         | 3-4            |

  Scenario Outline: checking banners
    When The user clicks <button name>
    Then The user sees the new banner after clicking <button name>

    Examples:
      | button name |
      | button-prev |
      | button-next |

  Scenario Outline: Create pizza from two halves
    When Button Choose pizza is pressed
    And Left half of pizza <pizzaOne> is pressed
    And Right half of pizza <pizzaTwo> is pressed
    And Add to Cart button is pressed in combiner
    And Menu button is pressed
    Then Combined pizza <pizzaOne> and <pizzaTwo> is added to Cart

    Examples:
      | pizzaOne     | pizzaTwo   |
      | Meat Delight | Vegetarian |
      | Vegetarian   | 4 Cheese   |

  Scenario Outline: Change crust of pizza
    When Pizza's <name> size button <size> is pressed
    And Pizza's <name> cheese crust button is pressed
    And Pizza <name> with cheese crust button is added to cart
    Then Pizza description in cart has Cheese crust

    Examples:
      | name        | size  |
      | Greek       | 35    |
      | Pepperoni   | 40    |