@ui
Feature: UI tests

  Scenario: Authorization
    Given User opens login page
    When User types LOGIN 'admin1' and PASSWORD '[9k<k8^z!+$$GkuP'
    And User clicks login button on Login page
    Then Dashboard is opened
    And User is authorized as 'admin1'

  Scenario: Open list of players
    Given User opens login page
    And User types LOGIN 'admin1' and PASSWORD '[9k<k8^z!+$$GkuP'
    And User clicks login button on Login page
    When User opens list of players
    Then Players list is loaded

  Scenario: Sort
    Given User opens login page
    And User types LOGIN 'admin1' and PASSWORD '[9k<k8^z!+$$GkuP'
    And User clicks login button on Login page
    And User opens list of players
    When User click sort by column
    Then List is sorted correct
