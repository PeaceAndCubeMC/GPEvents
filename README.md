# GPEvents

## Example

<details><summary>Create example</summary>

```
example_event:
  type: create

  player: "@p"
  world: world
  area: 100
  greater_pos:
    x:
      min: 0
      max: 10
    z:
      min: 0
      max: 10
  lesser_pos:
    x: 0
    z: 0
  claim_owner: "@p"
  is_subdivision: false

  commands:
    - example_command1
    - example_command2
```

</details>

## Format

<table>
  <tr>
    <th colspan="2">Parameter</th>
    <th>Description</th>
    <th>Possible values</th>
    <th>Used by</th>
  </tr>
  <tr>
    <td colspan="2"><code>type</code></td>
    <td>Type of event</td>
    <td>create | delete | resize | trust | untrust | command</td>
    <td>all</td>
  </tr>
  <tr>
    <td colspan="2"><code>player</code></td>
    <td>The player triggering the event</td>
    <td>A simple selector (like @p) or a username</td>
    <td>create, command</td>
  </tr>
  <tr>
    <td colspan="2"><code>world</code></td>
    <td>Name of the world the event occurs</td>
    <td>world | world_nether | world_the_end | ...</td>
    <td>all except command</td>
  </tr>
  <tr>
    <td colspan="2"><code>area</code></td>
    <td>Area of the claim after the event</td>
    <td>An integer</td>
    <td>all except command</td>
  </tr>
  <tr>
    <td rowspan="2"><code>greater_pos</code></td>
    <td><code>x</code></td>
    <td>X coordinate of the greater corner</td>
    <td rowspan="2">An integer or an object composed of a <code>min</code> and a <code>max</code></td>
    <td rowspan="2">all except command</td>
  </tr>
  <tr>
    <td><code>z</code></td>
    <td>Z coordinate of the greater corner</td>
  </tr>
  <tr>
    <td rowspan="2"><code>lesser_pos</code></td>
    <td><code>x</code></td>
    <td>X coordinate of the lesser corner</td>
    <td rowspan="2">An integer or an object composed of a <code>min</code> and a <code>max</code></td>
    <td rowspan="2">all except command</td>
  </tr>
  <tr>
    <td><code>z</code></td>
    <td>Z coordinate of the lesser corner</td>
  </tr>
  <tr>
    <td colspan="2"><code>claim_owner</code></td>
    <td>The player owning the claim</td>
    <td>A simple selector (like @p) or a username</td>
    <td>all except command</td>
  </tr>
  <tr>
    <td colspan="2"><code>is_subdivision</code></td>
    <td>Whether the claim is a subdivision</td>
    <td>true | false</td>
    <td>all except command</td>
  </tr>
  <tr>
    <td colspan="2"><code>trust_permission</code></td>
    <td>Type of permission given</td>
    <td>build | container | access</td>
    <td>trust</td>
  </tr>
  <tr>
    <td colspan="2"><code>trust_target</code></td>
    <td>The player being trusted or untrusted</td>
    <td>A simple selector (like @p) or a username</td>
    <td>trust, untrust</td>
  </tr>
  <tr>
    <td colspan="2"><code>player_command</code></td>
    <td>Command typed by the player</td>
    <td>A command with the slash</td>
    <td>command</td>
  </tr>
  <tr>
    <td rowspan="3"><code>player_position</code></td>
    <td><code>x</code></td>
    <td>X coordinate of the player position</td>
    <td rowspan="3">An integer or an object composed of a <code>min</code> and a <code>max</code></td>
    <td rowspan="3">command</td>
  </tr>
  <tr>
    <td><code>y</code></td>
    <td>Y coordinate of the player position</td>
  </tr>
  <tr>
    <td><code>z</code></td>
    <td>Z coordinate of the player position</td>
  </tr>
  <tr>
    <td colspan="2"><code>commands</code></td>
    <td>Commands that should be executed if all conditions match</td>
    <td>A list of commands without the slash</td>
    <td>all</td>
  </tr>
</table>
