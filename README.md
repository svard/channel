# channel
A rudimentary, kind of clojure core.async channel implementation in java

Output from demo
```bash
Putting value 42 into channel
Took value: 42
Took value: 42
```

The producer puts the value 42 into the channel. Consumer1 takes the value 42 directly off the channel 
while consumer2 takes the value 42 off a multiple of the channel.
