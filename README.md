# ConditionalPlaceholders
Placeholders with output based on other placeholders' values.

## Description

Ever wanted to display a text in a hologram, scoreboard, menu or in tablist based on a player statistic, amount of currency he has, or... anything else?
This plugin has been made just for that, it takes PlaceholderAPI placeholdes and based on the value outputs a text in another placeholder! Simple enough, isn't it?!

**ex.:** Display messages in scoreboard based on players health. If the player is under 3 health, display `You're about to die!`, when he's above three, but below 6, display `You're still fiiine..`,
when he's above 6, display `Healthy as ever!` Got it? Good.

## Installation

As any other standalone plugin, drop into `plugins/` and restart the server.\
*Note: plugin will start up without PlaceholderAPI installed, but it'd be useless,... so install it!*

## Features

### Placeholder settings

Settings are what makes this possible, they take an input placeholder and rules.\
They're located in [settings.yml](https://github.com/Wertik/ConditionalText/blob/master/src/main/resources/settings.yml)

```yaml
setting-one:
  placeholder: '%player_health%'
  rules:
    - "<3;&cYou're about to die!"
    - "<6;&eYou're fiiiine you crybaby!"
    - "&aHealthy as ever!"
```

Just create a new section with the setting name in the file and let's get started!\
*Note: Never use underscores in the setting names.*

#### Input placeholder

It can be anything you'd want to use, just make sure it's registered in PlaceholderAPI.

Enter the placeholder __with__ percentage signs. ``placeholder: %player_health%``

The output of this placeholder when parsed will be used in rules below to figure out which text to display, it can be string (text), or numbers (decimals included).

### Rules

Every rule is made up from a condition (in front of the semicolon) and the output text (everything after).\
When figuring out which text to use, the plugin goes from the top down and whichever condition first passes is used.

#### Conditions

Conditions should always have a valid operator on the left side and the limit number on the right. ``<10, >50, !=100``

Any type operators: `empty, !empty` (check if a placeholder returns empty value)\
Valid string operators: `==, !=`\
Valid number operators: `==, !=, <, >, <=, >=, %`\
Valid time operators: `==, !=, <, >, <=, >=`

When no condition is specified, it's automatically condidered true -- passing. \
Always try to provide one rule with no condition to make sure there's always some output.

```yaml
rules:
  - '<1000;&7You have under a thousand bucks, so poor.'
  - '>1000000;&6Over a million,.. now woow.'
  - '&7On your way to a million huh.'
```

You can also use other placeholders in conditions.
``- '<%some_placeholder%;&7Some text.'``

With strings:
*Let's say a placeholder outputs yes or no.*
```yaml
rules:
  - '==yes;&aYup yup.'
  - '==no;&cNaaaah.'
```

With time:
```yaml
rules:
  - '<10:0:0;&7&ogooob moorning.'
  - '<14:0:0;&7&otime for lunch, munch munch.'
  - '&7&oNap tiiiime!'
```

When using time, make sure you have the right time format configured in [config.yml](https://github.com/Wertik/ConditionalText/blob/master/src/main/resources/config.yml)

#### Output text

Output text is what's displayed if the conditions are met. It can contain colors (yes, 1.16 hex colors supported) and more PlaceholderAPI placeholders, which will be parsed.

**ex.:** ``- "<200;{#ffff9a}A cool hex color! &7With a random placeholder &8- &d%player_exp%"``

### Using the placeholders

After you've defined your settings, let's use them!\
But first, load the new settings in with a quick `/ct reload`.

Then just use the placeholder ``%conditionaltext_<setting>%`` whereever you desire. (And where PlaceholderAPI is supported)

### Arguments in placeholders

It's a useful feature when you want to use one setting for more variations of the same placeholder, for example displaying the amount of keys on multiple crates. 
It would be painful to create a setting for each of the crate types.

That's when arguments come in handy:
```yaml
custom-arg-setting:
  placeholder: '%specializedcrates_virtual_keys_$0%'
  rules:
    - "==0;&cYou have no keys :'("
    - "==1;&7&oOoOoo, one key, open it!"
    - "<10;&7You have &b%specializedcrates_virtual_keys_$0% &7keys."
    - ">50;&b%specializedcrates_virtual_keys_$0% &7keys, are you joking? Why are you not gambling you key-hugger?!?!"
```
*Example with [Specialized crates](https://www.spigotmc.org/resources/specialized-crates-1-8-1-16.9047/)*

To provide the `$0` argument, add another param to the placeholder: ``%conditionaltext_<setting>_(args)%``\
You can add as many of them as you want. Just make sure you're **counting from 0** and seperating each one with an underscore.

For the above example with a crate named Epic to display, you'd use ``%conditionaltext_custom-arg-setting_Epic%``
Then with a different type of crate,.. let's say Mythic: ``%conditionaltext_custom-arg-setting_Mythic%``

### Common problems

**Performance**
Due to the plugin requesting placeholders from other expansions through PlaceholderAPI again, it gets a lot of blame it doesn't deserve. Some of this is also caused by a lot of requests for the placeholders. When used in a scoreboard / tablist plugin, first try decreasing the refresh rate.

### Help

For help, join [this discord server](https://discord.gg/5Suw58j)
