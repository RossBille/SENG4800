## Input JSON
	[
	{
		"id":0,
		"name":"Level One",
		"background_id":1,
		"objectList":
		{
			"objects": [
			{
				"id":"0",
				"name":"Object 0",
				"sprite_id":"101",
				"shape":"circle",
				"visible":true,
				"start_pos":{"x":0, "y":0},
				"start_vel":"100",
				"start_acc":"200"
			},
			{
				"id":"1",
				"name":"Object 1",
				"sprite_id":"101",
				"shape":"circle",
				"visible":true,
				"start_pos":{"x":0,
				"y":0},
				"start_vel":"100",
				"start_acc":"200"
			},
			{
				"id":"2",
				"name":"Object 2",
				"sprite_id":"101",
				"shape":"circle",
				"visible":true,
				"start_pos":{"x":0,
				"y":0},
				"start_vel":"100",
				"start_acc":"200"
			},
			{
				"id":"3",
				"name":"Object 3",
				"sprite_id":"101",
				"shape":"circle",
				"visible":true,
				"start_pos":{"x":0,
				"y":0},
				"start_vel":"100",
				"start_acc":"200"
			}
			]
		},
		"events":[
		{
			"id":0,
			"type":"collision",
			"actions":[
			{
				"id":0,
				"type":"collision"
			},
			{
				"id":1,
				"type":"collision"
			},
			{
				"id":2,
				"type":"collision"
				}
			]
		}
		]	
	}
	]

## Output XML
	<?xml version="1.0" encoding="UTF-8"?>
	<levels>
		<id>0</id>
		<name>Level One</name>
		<background_id>1</background_id>
		<objectList>
			<objects>
				<id>0</id>
				<name>Object 0</name>
				<sprite_id>101</sprite_id>
				<shape>circle</shape>
				<visible>true</visible>
				<start_pos>
					<x>0</x>
					<y>0</y>
				</start_pos>
				<start_vel>100</start_vel>
				<start_acc>200</start_acc>
			</objects>
			<objects>
				<id>1</id>
				<name>Object 1</name>
				<sprite_id>101</sprite_id>
				<shape>circle</shape>
				<visible>true</visible>
				<start_pos>
					<x>0</x>
					<y>0</y>
				</start_pos>
				<start_vel>100</start_vel>
				<start_acc>200</start_acc>
			</objects>
			<objects>
				<id>2</id>
				<name>Object 2</name>
				<sprite_id>101</sprite_id>
				<shape>circle</shape>
				<visible>true</visible>
				<start_pos>
					<x>0</x>
					<y>0</y>
				</start_pos>
				<start_vel>100</start_vel>
				<start_acc>200</start_acc>
			</objects>
			<objects>
				<id>3</id>
				<name>Object 3</name>
				<sprite_id>101</sprite_id>
				<shape>circle</shape>
				<visible>true</visible>
				<start_pos>
					<x>0</x>
					<y>0</y>
				</start_pos>
				<start_vel>100</start_vel>
				<start_acc>200</start_acc>
			</objects>
		</objectList>
		<events>
			<id>0</id>
			<type>collision</type>
			<actions>
				<id>0</id>
				<type>collision</type>
			</actions>
			<actions>
				<id>1</id>
				<type>collision</type>
			</actions>
			<actions>
				<id>2</id>
				<type>collision</type>
			</actions>
		</events>
	</levels>