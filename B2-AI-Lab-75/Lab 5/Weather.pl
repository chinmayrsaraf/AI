% Weather forecast knowledge base

% Weather conditions: sunny, cloudy, rainy, snowy
% Temperature ranges: cold, moderate, warm, hot

weather(city, day, condition, temperature) :-
    city = 'Your City',
    day = 'Monday',
    condition = sunny,
    temperature = warm.

weather(city, day, condition, temperature) :-
    city = 'Your City',
    day = 'Tuesday',
    condition = cloudy,
    temperature = moderate.

weather(city, day, condition, temperature) :-
    city = 'Your City',
    day = 'Wednesday',
    condition = rainy,
    temperature = moderate.

weather(city, day, condition, temperature) :-
    city = 'Your City',
    day = 'Thursday',
    condition = snowy,
    temperature = cold.

% Weather forecast predicates

is_good_weather(City, Day) :-
    weather(City, Day, Condition, _),
    (Condition = sunny; Condition = cloudy),
    write('The weather in '), write(City), write(' on '), write(Day), write(' is good.').

is_cold_weather(City, Day) :-
    weather(City, Day, _, Temperature),
    Temperature = cold,
    write('Expect cold weather in '), write(City), write(' on '), write(Day), write('.').

is_moderate_weather(City, Day) :-
    weather(City, Day, _, Temperature),
    Temperature = moderate,
    write('Expect moderate weather in '), write(City), write(' on '), write(Day), write('.').

is_warm_weather(City, Day) :-
    weather(City, Day, _, Temperature),
    Temperature = warm,
    write('Expect warm weather in '), write(City), write(' on '), write(Day), write('.').

is_hot_weather(City, Day) :-
    weather(City, Day, _, Temperature),
    Temperature = hot,
    write('Expect hot weather in '), write(City), write(' on '), write(Day), write('.').

% Example usage
:- is_good_weather('Your City', 'Monday').
:- is_cold_weather('Your City', 'Thursday').
